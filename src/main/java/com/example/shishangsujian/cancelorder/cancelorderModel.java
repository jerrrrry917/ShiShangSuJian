package com.example.shishangsujian.cancelorder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class cancelorderModel {
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
}
