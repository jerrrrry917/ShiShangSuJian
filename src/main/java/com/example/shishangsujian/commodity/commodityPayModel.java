package com.example.shishangsujian.commodity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "commodityOrder")
public class commodityPayModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "commodityId")
    private int commodityId;

    @Column(name = "userId")
    private int userId;

    @Column(name = "staffId")
    private int staffId;

    @Column(name = "price")
    private double price;

    @Column(name = "finishTime")
    private Date finishTime;

    @Column(name = "count")
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
