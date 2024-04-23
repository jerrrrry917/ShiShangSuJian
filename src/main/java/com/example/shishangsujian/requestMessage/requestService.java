package com.example.shishangsujian.requestMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
public class requestService {
    @Autowired
    private requestRepository repository;

    @Value("${wechat.appid}")
    private String appId;

    @Value("${wechat.secret}")
    private String secret;

    public void requestSuccess(long id) {
        requestModel user = repository.findById(id);
        if (user != null) {
            user.setRequest(1);
            repository.save(user);
        }
    }

    public void sentReset(long id){
        requestModel user = repository.findById(id);
        if (user != null) {
            user.setRequest(0);
            repository.save(user);
        }
    }

    public int checkRequest(long id) {
        requestModel user = repository.findById(id);
        Integer requestValue = user != null ? user.getRequest() : null;

        if (requestValue != null && requestValue == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public void sendSubscriptionMessage(long userId, String templateId, String storeName, String appointmentItem, String cancelTime) {
        requestModel user = repository.findById(userId);
        if (user == null || user.getOpenid() == null) {
            System.err.println("无法找到用户或用户的openid为空");
            return;
        }
        String openid = user.getOpenid();
        String accessToken = getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + accessToken;

        // 直接构建消息数据
        Map<String, Map<String, Object>> data = buildSubscriptionData(storeName, appointmentItem, cancelTime);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("touser", openid);
        requestBody.put("template_id", templateId);
        requestBody.put("data", data);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        System.out.println(response.getBody());
    }

    private String getAccessToken() {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", appId, secret);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        Map responseBody = response.getBody();
        return responseBody != null ? (String) responseBody.get("access_token") : null;
    }

    // 新增辅助方法构建data部分
    public Map<String, Map<String, Object>> buildSubscriptionData(String storeName, String appointmentItem, String cancelTime) {
        Map<String, Map<String, Object>> data = new HashMap<>();

        data.put("thing5", createDataItem(storeName)); // 门店
        data.put("thing10", createDataItem(appointmentItem)); // 预约项目
        data.put("date7", createDataItem(cancelTime)); // 取消时间
        data.put("thing6", createDataItem("预约时间过长未到场")); // 取消原因
        data.put("phrase11", createDataItem("已取消")); // 预约状态

        return data;
    }

    // 创建data项的辅助方法
    private Map<String, Object> createDataItem(String value) {
        Map<String, Object> item = new HashMap<>();
        item.put("value", value);
        return item;
    }
}
