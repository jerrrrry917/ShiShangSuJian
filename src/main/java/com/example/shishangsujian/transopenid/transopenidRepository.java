package com.example.shishangsujian.transopenid;

import org.springframework.data.jpa.repository.JpaRepository;

public interface transopenidRepository extends JpaRepository<transopenidModel,Long> {
    transopenidModel findByOpenid(String openid);
}
