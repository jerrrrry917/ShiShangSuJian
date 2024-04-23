package com.example.shishangsujian.countWaiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class waitingService {
    @Autowired
    private waitingRepository repository;

    public int findWaitings(String staffId, String status) {
        // 使用repository来根据staffId和status查找订单
        List<waitingModel> waitings = repository.findByStaffIdAndStatus(staffId, status);
        // 返回找到的订单数量
        return waitings.size();
    }

    public int findbefore(String staffId, long orderId) {
        // 调用repository的方法获取满足条件的订单列表
        List<waitingModel> orders = repository.findByStaffIdAndStatusAndIdLessThan(staffId, "0", orderId);

        // 返回这些订单的数量
        return orders.size();
    }

    public int calculateWaitingTime(String staffId, long orderId) {
        // 获取状态为0（等待中）的订单列表
        List<waitingModel> orders = repository.findByStaffIdAndStatusAndIdLessThan(staffId, "0", orderId);
        // 获取状态为1（正在服务中）的订单列表
        List<waitingModel> orders1 = repository.findByStaffIdAndStatus(staffId, "1");

        int totalWaitingTime = 0;

        // 计算状态为0的订单等待时间
        for (waitingModel order : orders) {
            switch (order.getProductClassId()) {
                case "1":
                    totalWaitingTime += 20;
                    break;
                case "4":
                    totalWaitingTime += 60;
                    break;
                case "5":
                    totalWaitingTime += 30;
                    break;
                default:
                    totalWaitingTime += 20; // 默认时间，根据需要调整
                    break;
            }
        }

        // 如果存在正在服务中的订单，根据其productClassId增加相应的时间
        // 假设只有一个正在服务中的订单，如果有多个，可以适当调整逻辑
        for (waitingModel order : orders1) {
            switch (order.getProductClassId()) {
                case "1":
                    totalWaitingTime += 10;
                    break;
                case "4":
                    totalWaitingTime += 30;
                    break;
                case "5":
                    totalWaitingTime += 15;
                    break;
                default:
                    totalWaitingTime += 10;
                    break;
            }
        }

        return totalWaitingTime;
    }

}
