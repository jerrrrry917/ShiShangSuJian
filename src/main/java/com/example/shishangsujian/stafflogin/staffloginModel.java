package com.example.shishangsujian.stafflogin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "staff")
public class staffloginModel {
    @Id
    @Column(name = "id")
    private long Id;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "password")
    private String password;

    @Column(name = "code")
    private String code;

    @Column(name = "nickname")
    private String nickname;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
