package com.example.shishangsujian.memberamount;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "memberamount")
public class memberamountModel {
    @Id
    @Column(name = "id")
    private long id;//序列号

    @Column(name = "memberId")
    private String memberId;//关联会员

    @Column(name = "money")
    private Double money;//操作金额

    @Column(name = "optName")
    private String optName;//操作者

    @Column(name = "optType")
    private String optType;//操作项

    @Column(name = "type")
    private String type;//操作类型

    @Column(name = "optTime")
    private LocalDateTime optTime;//操作时间

    @Column(name = "remark")
    private String remark;//备注

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getOptName() {
        return optName;
    }

    public void setOptName(String optName) {
        this.optName = optName;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getOptTime() {
        return optTime;
    }

    public void setOptTime(LocalDateTime optTime) {
        this.optTime = optTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
