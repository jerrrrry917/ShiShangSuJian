package com.example.shishangsujian;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NormalOrderRepository extends JpaRepository<NormalOrderModel,Long> {
    NormalOrderModel findById(long id);

    NormalOrderModel findByOutTradeNo(String outtradeno);

    NormalOrderModel findBySerial(String serial);
}
