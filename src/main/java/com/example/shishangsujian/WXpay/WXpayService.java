package com.example.shishangsujian.WXpay;

import com.example.shishangsujian.NormalOrderModel;
import com.example.shishangsujian.NormalOrderRepository;
import com.example.shishangsujian.UserModel;
import com.example.shishangsujian.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WXpayService {

    @Autowired
    private WXpayRepository wxpayRepository;

    @Autowired
    private NormalOrderRepository normalOrderRepository;

    @Autowired
    private UserRepository userRepository;

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

    public Map<String, String> createPrepayOrder(double price,String name,long orderid,HttpSession session) {
        WXpayModel config = wxpayRepository.findById(2);
        NormalOrderModel checkorder=normalOrderRepository.findById(orderid);
        UserModel user=userRepository.findById(checkorder.getMemberId());

        if (user.getBenefit() != null && user.getBenefit() > 0) {
            price -= user.getBenefit();
        }

        Map<String, String> params = new HashMap<>();
        params.put("appid", config.getAppId());
        params.put("mch_id", config.getMchId());
        params.put("nonce_str", UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32));
        params.put("body", name);//付款介绍（商品名）
        params.put("out_trade_no", UUID.randomUUID().toString().replaceAll("-", ""));
        params.put("total_fee", String.valueOf(Math.round(price * 100)));  // 这里实际金额x100
        params.put("spbill_create_ip", "121.41.97.240");
        params.put("notify_url", "https://www.teleworklink.com:8443/api/pay/notification"); //支付成功回调url
        params.put("trade_type", "JSAPI");
        params.put("openid", session.getAttribute("openid").toString());//付款者openid

        NormalOrderModel order = normalOrderRepository.findById(orderid);
        if (order != null) {
            order.setOutTradeNo(params.get("out_trade_no"));
            normalOrderRepository.save(order);
        } else {
            throw new RuntimeException("Order not found with id: " + orderid);
        }


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
            // 这里处理API调用本身的错误
            throw new RuntimeException("Failed to create prepay order. Return msg: " + responseMap.get("return_msg"));
        }

        if (!"SUCCESS".equals(responseMap.get("result_code"))) {
            // 这里处理API调用成功，但是订单创建失败的情况
            throw new RuntimeException("Failed to create prepay order. Error code: " + responseMap.get("err_code") + ". Error description: " + responseMap.get("err_code_des"));
        }

        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);  // Current Unix timestamp
        String prepay_id = responseMap.get("prepay_id");  // Get prepay_id from the response

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

    /**
     * 处理微信支付的回调通知
     * @param xmlData 从微信接收到的XML格式的通知数据
     * @return 应答XML，告诉微信成功接收到了通知
     */
    public String handlePaymentNotification(String xmlData) {
        Map<String, String> notificationData = parseXMLResponse(xmlData);

        // 1. 验证商户号
        String receivedMchId = notificationData.get("mch_id");
        String expectedMchId = wxpayRepository.findById(2).getMchId();

        if (!expectedMchId.equals(receivedMchId)) {
            // 商户号不匹配，可能是伪造的通知
            System.out.println("WXcallback check fail - Invalid mch_id");
            return generateResponseXML("FAIL", "Invalid mch_id");
        }

        //2.处理订单信息
        String outTradeNo = notificationData.get("out_trade_no");
        NormalOrderModel order = normalOrderRepository.findByOutTradeNo(outTradeNo);
        if (order != null) {
            order.setStatus("3");
            // 获取当前时间
            LocalDateTime now = LocalDateTime.now();
            order.setPay_time(now);
            order.setType(0);
            normalOrderRepository.save(order);

            UserModel user=userRepository.findById(order.getMemberId());
            user.setBenefit(0);
            userRepository.save(user);
        }
        else {
            System.out.println("Order not found for out_trade_no: " + outTradeNo);
            return generateResponseXML("FAIL", "Order not found");
        }

        System.out.println("WXcallback check successfully");
        // 3. 向微信发送响应
        return generateResponseXML("SUCCESS", "OK");
    }



    /**
     * 生成应答微信的XML
     * @param returnCode 返回代码，"SUCCESS" 或 "FAIL"
     * @param returnMsg 返回信息
     * @return XML格式的应答
     */
    private String generateResponseXML(String returnCode, String returnMsg) {
        return "<xml>" +
                "<return_code><![CDATA[" + returnCode + "]]></return_code>" +
                "<return_msg><![CDATA[" + returnMsg + "]]></return_msg>" +
                "</xml>";
    }
}
