package com.example.shishangsujian.setProfile;

import jakarta.persistence.*;

@Entity
@Table(name = "wxprofile")
public class setProfileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "memberId")
    private int memberId;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "photo")
    private String photo;


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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
