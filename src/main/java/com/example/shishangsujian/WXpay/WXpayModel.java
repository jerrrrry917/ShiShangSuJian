package com.example.shishangsujian.WXpay;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "settings")
public class WXpayModel {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "domain")
    private String domain;

    @Column(name = "appId")
    private String appId;

    @Column(name = "appSecret")
    private String appSecret;

    @Column(name = "mchId")
    private String mchId;

    @Column(name = "apiKey")
    private String apiKey;

    @Column(name = "token")
    private String token;

    @Column(name = "ticket")
    private String ticket;

    @Column(name = "sms")
    private String sms;

    @Column(name = "smspwd")
    private String smspwd;

    @Column(name = "postUrl")
    private String postUrl;

    @Column(name = "wxmenu", columnDefinition = "TEXT")
    private String wxmenu;

    @Column(name = "adminMobile")
    private String adminMobile;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDomain() {
        return domain;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getMchId() {
        return mchId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getToken() {
        return token;
    }

    public String getTicket() {
        return ticket;
    }

    public String getSms() {
        return sms;
    }

    public String getSmspwd() {
        return smspwd;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public String getWxmenu() {
        return wxmenu;
    }

    public String getAdminMobile() {
        return adminMobile;
    }
}

