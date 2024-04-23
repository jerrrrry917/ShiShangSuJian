package com.example.shishangsujian.requestMessage;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "member")
public class requestModel {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "openid")
    private String openid;

    @Column(name = "request",nullable = true)
    private Integer request;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getRequest() {
        return request;
    }

    public void setRequest(Integer request) {
        this.request = request;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
