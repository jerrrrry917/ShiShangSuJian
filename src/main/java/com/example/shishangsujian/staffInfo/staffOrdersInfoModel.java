package com.example.shishangsujian.staffInfo;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class staffOrdersInfoModel {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "memberId")
    private Integer memberId;

    @Column(name = "serial")
    private String serial;

    @Column(name = "shopName")
    private String shopName;

    @Column(name = "money")
    private double money;

    @Column(name = "optName")
    private String optName;

    @Column(name = "type")
    private Integer type;

    @Column(name = "shopId")
    private String shopId;

    @Column(name = "createtime")
    private String createtime;

    @Column(name = "staffId")
    private String staffId;

    @Column(name = "productClassId")
    private String productClassId;

    @Column(name = "status")
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getOptName() {
        return optName;
    }

    public void setOptName(String optName) {
        this.optName = optName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
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


}
