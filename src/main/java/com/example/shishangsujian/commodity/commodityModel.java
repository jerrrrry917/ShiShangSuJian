package com.example.shishangsujian.commodity;

import jakarta.persistence.*;

@Entity
@Table(name = "commodity")
public class commodityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "photoURL", nullable = false, length = 255)
    private String photoURL;

    // 无参构造函数
    public commodityModel() {
    }

    // 带所有参数的构造函数
    public commodityModel(double price, String description, String photoURL) {
        this.price = price;
        this.description = description;
        this.photoURL = photoURL;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }
}
