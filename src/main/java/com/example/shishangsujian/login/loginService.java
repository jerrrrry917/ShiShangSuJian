package com.example.shishangsujian.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class loginService {
    @Value("${wechat.appid}")
    private String appId;

    @Value("${wechat.secret}")
    private String secret;


    public void getOpenIdUnionIdAndSessionKey(String code, HttpSession session) {
        try {
            String url = String.format("https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code", appId, secret, code);

            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);

            loginModel loginResponse = new ObjectMapper().readValue(response, loginModel.class);

            // 将openid和session_key存入session中
            if (loginResponse.getOpenid() != null) {
                session.setAttribute("openid", loginResponse.getOpenid());
                System.out.println("openid: " + loginResponse.getOpenid());
            }

            if (loginResponse.getSession_key() != null) {
                session.setAttribute("session_key", loginResponse.getSession_key());
                System.out.println("session_key: " + loginResponse.getSession_key());
            }

            if (loginResponse.getUnionid() != null) {
                session.setAttribute("unionid", loginResponse.getUnionid());
                System.out.println("unionid: " + loginResponse.getUnionid());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
