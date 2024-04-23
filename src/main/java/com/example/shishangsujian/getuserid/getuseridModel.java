package com.example.shishangsujian.getuserid;

import jakarta.persistence.*;

@Entity
@Table(name = "member")
public class getuseridModel {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "openid")
    private String openid;

    @Column(name = "unionid")
    private String unionid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
