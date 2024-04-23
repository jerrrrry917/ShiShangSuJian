package com.example.shishangsujian.staffInfo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "membergrade")
public class staffReplyModel {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "memberId")
    private String memberId;

    @Column(name = "staffId")
    private Integer staffId;

    @Column(name = "ordersId")
    private Integer ordersId;

    @Column(name = "createtime")
    private Date createtime;

    @Column(name = "sumValue")
    private Integer sumValue;

    @Column(name = "answers")
    private String answers;

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

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getSumValue() {
        return sumValue;
    }

    public void setSumValue(Integer sumValue) {
        this.sumValue = sumValue;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }
}
