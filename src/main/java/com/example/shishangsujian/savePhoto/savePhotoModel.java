package com.example.shishangsujian.savePhoto;

import jakarta.persistence.*;

@Entity
@Table(name = "wxphoto")
public class savePhotoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "memberId")
    private int memberId;

    @Column(name = "name")
    private String name;

    @Column(name = "photoFront")
    private String photoFront;

    @Column(name = "photoSide")
    private String photoSide;

    @Column(name = "photoBack")
    private String photoBack;

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

    public String getPhotoFront() {
        return photoFront;
    }

    public void setPhotoFront(String photoFront) {
        this.photoFront = photoFront;
    }

    public String getPhotoSide() {
        return photoSide;
    }

    public void setPhotoSide(String photoSide) {
        this.photoSide = photoSide;
    }

    public String getPhotoBack() {
        return photoBack;
    }

    public void setPhotoBack(String photoBack) {
        this.photoBack = photoBack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
