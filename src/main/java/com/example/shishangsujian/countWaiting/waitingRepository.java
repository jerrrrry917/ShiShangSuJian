package com.example.shishangsujian.countWaiting;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface waitingRepository extends JpaRepository<waitingModel,Long> {
    List<waitingModel> findByStaffIdAndStatus(String staffId,String status);

    // 新方法：根据staffId和status找到所有状态为0的订单，并从中筛选出id小于指定orderId的订单
    List<waitingModel> findByStaffIdAndStatusAndIdLessThan(String staffId, String status, Long orderId);
}
