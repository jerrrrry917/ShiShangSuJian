package com.example.shishangsujian.createcomment.membergrade;

import org.springframework.data.jpa.repository.JpaRepository;

public interface membergradeRepository extends JpaRepository<membergradeModel,Long> {
    membergradeModel findBySerial(String serial);

    membergradeModel findByOrdersId(int orderId);
}
