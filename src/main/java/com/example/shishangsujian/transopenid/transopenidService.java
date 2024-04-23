package com.example.shishangsujian.transopenid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class transopenidService {
    @Value("${gongzhonhao.appid}")
    private String appId;

    @Value("${gongzhonghao.secret}")
    private String secret;

    @Autowired
    private transopenidRepository repository;

    public void getAccessToken(HttpSession session) {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + secret;
        RestTemplate restTemplate = new RestTemplate();
        String responseStr = restTemplate.getForObject(url, String.class);

        if(responseStr == null || responseStr.isEmpty()) {
            throw new RuntimeException("Empty response from WeChat API");
        }

        System.out.println("WeChat API response: " + responseStr); // 添加这行来打印API响应

        ObjectMapper objectMapper = new ObjectMapper();
        AccessTokenResponse accessTokenResponse;
        try {
            accessTokenResponse = objectMapper.readValue(responseStr, AccessTokenResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse access token response", e);
        }

        String accessToken = accessTokenResponse.getAccessToken();
        if (accessToken == null || accessToken.isEmpty()) {
            throw new RuntimeException("Failed to retrieve access token");
        }

        session.setAttribute("accessToken",accessToken);
        //return accessToken;
    }


    public void getunionid1(String testid,HttpSession session){//保存版本，当testid从未出现时在数据库创建新行
        transopenidModel transopenid = repository.findByOpenid(testid);
        //String accessToken = getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + session.getAttribute("accessToken") + "&openid=" + testid + "&lang=zh_CN";
        RestTemplate restTemplate = new RestTemplate();
        String responseStr = restTemplate.getForObject(url, String.class);
        WechatResponse response = null;
        try {
            response = new ObjectMapper().readValue(responseStr, WechatResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        if (response != null) {
            if(response.getSubscribe() == 0){
                System.err.println("The user has not subscribed to the public account. OpenID: " + testid);
            }
            else if (response.getUnionid() != null) {
                if (transopenid == null) {
                    // 当transopenid为null时创建新的实例并设置openid和unionid
                    transopenid = new transopenidModel();
                    transopenid.setOpenid(testid);
                }
                // 设置unionid并保存实例到数据库
                transopenid.setUnionid(response.getUnionid());
                repository.save(transopenid);
            } else if (response.getErrcode() != null) {
                // 处理API响应中的错误
                System.err.println("Error calling WeChat API: " + response.getErrmsg());
            } else {
                // 处理其他未知错误
                System.err.println("Unknown error calling WeChat API");
            }
        } else {
            // 处理微信API调用失败的情况
            System.err.println("Failed to call WeChat API");
        }
    }


    public void getunionid2(String testid,HttpSession session) {//不保存版本，只更新testid存在的行
        transopenidModel transopenid = repository.findByOpenid(testid);

        if (transopenid == null) {
            // 如果transopenid为null，即openid没有在数据库中找到，打印一个消息并返回
            System.err.println("OpenID not found in the database: " + testid);
            return;
        }

        //String accessToken = getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + session.getAttribute("accessToken") + "&openid=" + testid + "&lang=zh_CN";
        RestTemplate restTemplate = new RestTemplate();
        String responseStr = restTemplate.getForObject(url, String.class);
        WechatResponse response = null;

        try {
            response = new ObjectMapper().readValue(responseStr, WechatResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        try {
            if (response != null) {
                if(response.getSubscribe() == 0){
                    System.err.println("The user has not subscribed to the public account. OpenID: " + testid);
                }
                else if (response.getUnionid() != null) {
                    // 设置unionid并保存实例到数据库
                    System.out.println("The unionid set successful.OpenID: "+testid);
                    transopenid.setUnionid(response.getUnionid());
                    repository.save(transopenid);
                } else if (response.getErrcode() != null) {
                    // 处理API响应中的错误
                    System.err.println("Error calling WeChat API: " + response.getErrmsg());
                } else {
                    // 处理其他未知错误
                    System.err.println("Unknown error calling WeChat API");
                    System.err.println("Response: " + responseStr);
                }
            } else {
                // 处理微信API调用失败的情况
                System.err.println("Failed to call WeChat API");
            }
        }

        catch (Exception e){
            System.err.println("Error parsing WeChat API response");
            System.err.println("Response: " + responseStr);
            e.printStackTrace();
        }
    }

    public String getunionid3(String testid, HttpSession session) {//测试用，查看微信端返回的json
        transopenidModel transopenid = repository.findByOpenid(testid);

        if (transopenid == null) {
            // 如果transopenid为null，即openid没有在数据库中找到，打印一个消息并返回
            System.err.println("OpenID not found in the database: " + testid);
            return "OpenID not found in the database: " + testid;
        }

        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + session.getAttribute("accessToken") + "&openid=" + testid + "&lang=zh_CN";
        RestTemplate restTemplate = new RestTemplate();
        String responseStr = restTemplate.getForObject(url, String.class);
        WechatResponse response = null;

        try {
            response = new ObjectMapper().readValue(responseStr, WechatResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        try {
            if (response != null) {
                if(response.getSubscribe() == 0){
                    System.err.println("The user has not subscribed to the public account. OpenID: " + testid);
                }
                else if (response.getUnionid() != null) {
                    System.out.println("The unionid set successful. OpenID: " + testid);
                    //transopenid.setUnionid(response.getUnionid());
                    //repository.save(transopenid);
                } else if (response.getErrcode() != null) {
                    // 处理API响应中的错误
                    System.err.println("Error calling WeChat API: " + response.getErrmsg());
                } else {
                    // 处理其他未知错误
                    System.err.println("Unknown error calling WeChat API");
                    System.err.println("Response: " + responseStr);
                }
            } else {
                // 处理微信API调用失败的情况
                System.err.println("Failed to call WeChat API");
            }
        }

        catch (Exception e){
            System.err.println("Error parsing WeChat API response");
            System.err.println("Response: " + responseStr);
            e.printStackTrace();
        }

        return responseStr;
    }

}

