package com.example.shishangsujian.ordersrecord;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ordersrecordRepository extends JpaRepository<ordersrecordModel,Long> {
    List<ordersrecordModel> findByMemberIdAndStatus(int memberId,String status);

    List<ordersrecordModel> findByMemberIdAndStatusOrMemberIdAndStatus(int memberId, String status1, int memberIdAgain, String status2);

    ordersrecordModel findBySerial(String serial);
}
