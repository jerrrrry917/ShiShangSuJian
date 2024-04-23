package com.example.shishangsujian;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jdk.jfr.Name;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class NormalOrderModel {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "memberId")
    private int memberId;

    @Column(name = "serial")
    private String serial;
    @Column(name = "numbers")
    private String numbers;

    @Column(name = "shopName")
    private String shopName;

    @Column(name = "money")
    private Double money;
    @Column(name = "optName")
    private String optName;

    @Column(name = "createtime")
    private String createtime;

    @Column(name = "staffId")
    private String staffId;

    @Column(name = "status")
    private String status;

    @Column(name = "pay_time")
    private LocalDateTime pay_time;

    @Column(name = "productClassId")
    private String productClassId;

    @Column(name = "outTradeNo")
    private String outTradeNo;

    @Column(name = "type")
    private Integer type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getPay_time() {
        return pay_time;
    }

    public void setPay_time(LocalDateTime pay_time) {
        this.pay_time = pay_time;
    }

    public String getProductClassId() {
        return productClassId;
    }

    public void setProductClassId(String productClassId) {
        this.productClassId = productClassId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
