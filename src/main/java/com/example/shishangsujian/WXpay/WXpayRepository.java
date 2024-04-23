package com.example.shishangsujian.WXpay;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WXpayRepository extends JpaRepository<WXpayModel,Long> {
    WXpayModel findById(long id);

}
