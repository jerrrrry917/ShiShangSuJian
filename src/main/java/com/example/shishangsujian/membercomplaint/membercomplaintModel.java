package com.example.shishangsujian.membercomplaint;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "membercomplaint")
public class membercomplaintModel {

    @Id
    @Column(name = "id")
    private long id;//序列号

    @Column(name = "memberId")
    private Integer memberId;//关联会员

    @Column(name = "staffId")
    private String staffId;//关联员工

    @Column(name = "ordersId")
    private String ordersId;//关联订单

    @Column(name = "createtime")
    private LocalDateTime createtime;//操作时间

    @Column(name = "title")
    private String title;//投诉标题

    @Column(name = "used")
    private String used;//是否处理（0未处理--1已处理）

    @Column(name = "shopId")
    private String shopId;//关联店铺

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

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}
