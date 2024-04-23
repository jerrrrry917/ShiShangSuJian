package com.example.shishangsujian.stafforders;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class staffordersModel {
    @Id
    @Column(name = "id")
    private long Id;

    @Column(name = "memberId")
    private Integer memberId;

    @Column(name = "numbers")
    private String numbers;

    @Column(name = "shopName")
    private String shopName;

    @Column(name = "type")
    private Integer type;

    @Column(name = "createtime" )
    private String createtime;

    @Column(name = "staffId")
    private String staffId;

    @Column(name = "productClassId")
    private String productClassId;

    @Column(name = "pay_time")
    private LocalDateTime pay_time;

    @Column(name = "status")
    private String status;

    @Column(name = "money")
    private double money;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
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

    public String getProductClassId() {
        return productClassId;
    }

    public void setProductClassId(String productClassId) {
        this.productClassId = productClassId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public LocalDateTime getPay_time() {
        return pay_time;
    }

    public void setPay_time(LocalDateTime pay_time) {
        this.pay_time = pay_time;
    }
}
