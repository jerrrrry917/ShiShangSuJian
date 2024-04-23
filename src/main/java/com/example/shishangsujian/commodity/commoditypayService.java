package com.example.shishangsujian.commodity;

import com.example.shishangsujian.WXpay.WXpayModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;
import com.example.shishangsujian.WXpay.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class commoditypayService {
    @Autowired
    private commodityPayRepository commodityPayRepository;

    @Autowired
    private WXpayRepository wxpayRepository;

    private final String prepayUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    private String generateXMLPayload(Map<String, String> params) {
        StringBuilder xml = new StringBuilder();
        xml.append("<xml>");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            xml.append("<").append(entry.getKey()).append(">")
                    .append(entry.getValue())
                    .append("</").append(entry.getKey()).append(">");
        }
        xml.append("</xml>");
        return xml.toString();
    }

    public Map<String, String> createPrepayOrder(double price, String name, int commodityId, int staffId, int count, HttpSession session) {
        WXpayModel config = wxpayRepository.findById(2);

        Map<String, String> params = new HashMap<>();
        params.put("appid", config.getAppId());
        params.put("mch_id", config.getMchId());
        params.put("nonce_str", UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32));
        params.put("body", name); //付款介绍（商品名）
        params.put("out_trade_no", UUID.randomUUID().toString().replaceAll("-", ""));
        params.put("total_fee", String.valueOf(Math.round(price * 100))); // 这里实际金额x100
        params.put("spbill_create_ip", "121.41.97.240"); // 服务器IP地址
        params.put("notify_url", "https://www.teleworklink.com:8443/api/commodity/pay/notify");//支付成功回调url
        params.put("trade_type", "JSAPI");
        params.put("openid", session.getAttribute("openid").toString());

        // 附加数据
        Map<String, Object> attachData = new HashMap<>();
        attachData.put("commodityId", commodityId);
        attachData.put("userId", session.getAttribute("Id"));
        attachData.put("staffId", staffId);
        attachData.put("price", price);
        attachData.put("count",count);
        // 将附加数据转换为JSON字符串
        String attach = null;
        try {
            attach = new ObjectMapper().writeValueAsString(attachData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        params.put("attach", attach);

        //签名
        String sign = generateSign(params, config.getApiKey());
        params.put("sign", sign);

        String xmlPayload = generateXMLPayload(params);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<String> request = new HttpEntity<>(xmlPayload, headers);

        String response = restTemplate.postForObject(prepayUrl, request, String.class);
        Map<String, String> responseMap = parseXMLResponse(response);

        if (!"SUCCESS".equals(responseMap.get("return_code"))) {
            throw new RuntimeException("Failed to create prepay order. Return msg: " + responseMap.get("return_msg"));
        }

        if (!"SUCCESS".equals(responseMap.get("result_code"))) {
            throw new RuntimeException("Failed to create prepay order. Error code: " + responseMap.get("err_code") + ". Error description: " + responseMap.get("err_code_des"));
        }

        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        String prepay_id = responseMap.get("prepay_id");

        Map<String, String> payParams = new HashMap<>();
        payParams.put("appId", config.getAppId());
        payParams.put("timeStamp", timeStamp);
        payParams.put("nonceStr", params.get("nonce_str"));
        payParams.put("package", "prepay_id=" + prepay_id);
        payParams.put("signType", "MD5");

        String paySign = generateSign(payParams, config.getApiKey());

        responseMap.put("timeStamp", timeStamp);
        responseMap.put("paySign", paySign);
        responseMap.put("nonceStr", params.get("nonce_str"));

        return responseMap;
    }

    //生成密钥
    private String generateSign(Map<String, String> params, String apiKey) {//生成sign
        // 排序并拼接参数
        String stringA = params.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
        String stringSignTemp = stringA + "&key=" + apiKey;
        // MD5加密并转为大写
        return DigestUtils.md5DigestAsHex(stringSignTemp.getBytes()).toUpperCase();
    }

    private Map<String, String> parseXMLResponse(String response) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(response)));
            NodeList elements = document.getDocumentElement().getChildNodes();

            Map<String, String> resultMap = new HashMap<>();
            for (int i = 0; i < elements.getLength(); i++) {
                Node node = elements.item(i);
                resultMap.put(node.getNodeName(), node.getTextContent());
            }
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //处理微信回调函数
    public String handlePaymentNotification(String xmlData) {
        Map<String, String> notificationData = parseXMLResponse(xmlData);

        // 1. 验证商户号
        String receivedMchId = notificationData.get("mch_id");
        String expectedMchId = wxpayRepository.findById(2).getMchId();
        if (!expectedMchId.equals(receivedMchId)) {
            System.out.println("WXcallback check fail - Invalid mch_id");
            return generateResponseXML("FAIL", "Invalid mch_id");
        }

        // 2. 从附加信息中获取订单信息并创建新的CommodityOrder
        String attach = notificationData.get("attach");
        try {
            Map<String, Object> attachData = new ObjectMapper().readValue(attach, HashMap.class);
            commodityPayModel newOrder = new commodityPayModel();

            // 对 commodityId 的类型检查和处理
            Object commodityIdObj = attachData.get("commodityId");
            if (commodityIdObj instanceof Integer) {
                newOrder.setCommodityId((Integer) commodityIdObj);
            } else if (commodityIdObj instanceof String) {
                newOrder.setCommodityId(Integer.parseInt((String) commodityIdObj));
            }

            // 对 userId 的类型检查和处理
            Object userIdObj = attachData.get("userId");
            if (userIdObj instanceof Integer) {
                newOrder.setUserId((Integer) userIdObj);
            } else if (userIdObj instanceof String) {
                newOrder.setUserId(Integer.parseInt((String) userIdObj));
            }

            // 对 staffId 的类型检查和处理
            Object staffIdObj = attachData.get("staffId");
            if (staffIdObj instanceof Integer) {
                newOrder.setStaffId((Integer) staffIdObj);
            } else if (staffIdObj instanceof String) {
                newOrder.setStaffId(Integer.parseInt((String) staffIdObj));
            }

            // 对 price 的类型检查和处理
            Object priceObj = attachData.get("price");
            if (priceObj instanceof Double) {
                newOrder.setPrice((Double) priceObj);
            } else if (priceObj instanceof String) {
                newOrder.setPrice(Double.parseDouble((String) priceObj));
            }

            Object countObj = attachData.get("count");
            if (countObj instanceof Integer) {
                newOrder.setCount((Integer) countObj);
            } else if (countObj instanceof String) {
                newOrder.setCount(Integer.parseInt((String) countObj));
            }

            newOrder.setFinishTime(new Date());
            // 保存新订单
            commodityPayRepository.save(newOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return generateResponseXML("FAIL", "Failed to process order information");
        }


        System.out.println("CommodityOrder created successfully");
        // 3. 向微信发送成功响应
        return generateResponseXML("SUCCESS", "OK");
    }

    private String generateResponseXML(String returnCode, String returnMsg) {
        return "<xml>" +
                "<return_code><![CDATA[" + returnCode + "]]></return_code>" +
                "<return_msg><![CDATA[" + returnMsg + "]]></return_msg>" +
                "</xml>";
    }
}
