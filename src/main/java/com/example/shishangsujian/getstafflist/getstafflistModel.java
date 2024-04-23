package com.example.shishangsujian.getstafflist;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "staff")
public class getstafflistModel {
    @Id
    @Column(name = "id")
    private long Id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "states")
    private int states;

    @Column(name = "shopId")
    private String shopId;

    @Column(name = "wxphoto")
    private String wxphoto;

    @Column(name = "level")
    private double level;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public int getStates() {
        return states;
    }

    public void setStates(int states) {
        this.states = states;
    }

    public String getWxphoto() {
        return wxphoto;
    }

    public void setWxphoto(String wxphoto) {
        this.wxphoto = wxphoto;
    }

    public double getLevel() {
        return level;
    }

    public void setLevel(double level) {
        this.level = level;
    }
}
