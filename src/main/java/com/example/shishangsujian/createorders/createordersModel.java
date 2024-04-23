package com.example.shishangsujian.createorders;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class createordersModel {
    @Id
    @Column(name = "id")
    private long id;//序列号

    @Column(name = "memberId")
    private int memberId;//关联会员

    @Column(name = "serial")
    private String serial;//预约单号

    @Column(name = "numbers")
    private String numbers;//排队号

    @Column(name = "shopName")
    private String shopName;//服务店

    @Column(name = "waitCount")
    private Integer waitCount;//等待人数

    @Column(name = "waitTime")
    private String waitTime;//等待时间

    @Column(name = "money")
    private Double money;//服务金额

    @Column(name = "optName")
    private String optName;//服务师傅

    @Column(name = "times")
    private Integer times;//服务时间

    @Column(name = "pay")
    private Integer pay;//支付方式（0微信--1积分--2资金--3现金）

    @Column(name = "type")
    private Integer type;//订单类型

    @Column(name = "ecode")
    private String ecode;//二维码

    @Column(name = "memberComplaintId")
    private String memberComplaintId;//关联投诉单

    @Column(name = "shopId")
    private String shopId;//店铺id

    @Column(name = "createtime")
    private String createtime;//订单创建时间

    @Column(name = "staffId")
    private String staffId;//师傅id

    @Column(name = "productClassId")
    private String productClassId;//服务类型

    @Column(name = "status")
    private String status;//订单状态

    @Column(name = "pay_time")
    private LocalDateTime payTime;//付款时间

    @Column(name = "apponitmentOrder")
    private Integer apponitmentOrder;//预约顺序调整

    @Column(name = "integral")
    private double integral;//积分

    @Column(name = "adminSendCount")
    private String adminSendCount;//后台消息推送次数

    @Column(name = "handleStaffId")
    private String handleStaffId;//处理师傅id

    @Column(name = "optName2")
    private String optName2;//服务师傅（确认订单付款用）

    @Column(name = "status2")
    private String status2;//订单状态

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

    public Integer getWaitCount() {
        return waitCount;
    }

    public void setWaitCount(Integer waitCount) {
        this.waitCount = waitCount;
    }

    public String getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(String waitTime) {
        this.waitTime = waitTime;
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

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Integer getPay() {
        return pay;
    }

    public void setPay(Integer pay) {
        this.pay = pay;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getEcode() {
        return ecode;
    }

    public void setEcode(String ecode) {
        this.ecode = ecode;
    }

    public String getMemberComplaintId() {
        return memberComplaintId;
    }

    public void setMemberComplaintId(String memberComplaintId) {
        this.memberComplaintId = memberComplaintId;
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

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }

    public Integer getApponitmentOrder() {
        return apponitmentOrder;
    }

    public void setApponitmentOrder(Integer apponitmentOrder) {
        this.apponitmentOrder = apponitmentOrder;
    }

    public double getIntegral() {
        return integral;
    }

    public void setIntegral(double integral) {
        this.integral = integral;
    }

    public String getAdminSendCount() {
        return adminSendCount;
    }

    public void setAdminSendCount(String adminSendCount) {
        this.adminSendCount = adminSendCount;
    }

    public String getHandleStaffId() {
        return handleStaffId;
    }

    public void setHandleStaffId(String handleStaffId) {
        this.handleStaffId = handleStaffId;
    }

    public String getOptName2() {
        return optName2;
    }

    public void setOptName2(String optName2) {
        this.optName2 = optName2;
    }

    public String getStatus2() {
        return status2;
    }

    public void setStatus2(String status2) {
        this.status2 = status2;
    }
}
