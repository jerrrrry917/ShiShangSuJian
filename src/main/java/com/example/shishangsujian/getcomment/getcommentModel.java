package com.example.shishangsujian.getcomment;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "membergrade")
public class getcommentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(name = "edits")
    private String edits;

    @Column(name = "shopId")
    private String shopId;

    @Column(name = "dictionaryId")
    private Integer dictionaryId;

    @Column(name = "optName")
    private String optName;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "serial")
    private String serial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getEdits() {
        return edits;
    }

    public void setEdits(String edits) {
        this.edits = edits;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Integer getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(Integer dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public String getOptName() {
        return optName;
    }

    public void setOptName(String optName) {
        this.optName = optName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
}
