package com.example.shishangsujian.getuserid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface getuseridRepository extends JpaRepository<getuseridModel, Long> {
    getuseridModel findByOpenid(String openid);

    getuseridModel findByUnionid(String unionid);

    @Query("SELECT COALESCE(MAX(u.id), 0) FROM getuseridModel u")
    Long findMaxId();
}
