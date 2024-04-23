package com.example.shishangsujian;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//通用用户实体
@Entity
@Table(name = "member")
public class UserModel {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "benefit")
    private Integer benefit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getBenefit() {
        return benefit;
    }

    public void setBenefit(Integer benefit) {
        this.benefit = benefit;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
