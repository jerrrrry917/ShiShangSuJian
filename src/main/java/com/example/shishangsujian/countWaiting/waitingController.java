package com.example.shishangsujian.countWaiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/findwait")
public class waitingController {
    @Autowired
    private waitingService service;

    @GetMapping("")
    public int getWaitings(@RequestParam String staffId,String status){
        return service.findWaitings(staffId,status);
    }

    // 新增方法：获取给定员工和订单ID之前的等待订单数量
    @GetMapping("/findBefore")
    public int findBefore(@RequestParam String staffId, @RequestParam long orderId) {
        return service.findbefore(staffId, orderId);
    }

    // 新增方法：计算给定员工和订单ID之前的总等待时间
    @GetMapping("/calculateWaitingTime")
    public int calculateWaitingTime(@RequestParam String staffId, @RequestParam long orderId) {
        return service.calculateWaitingTime(staffId, orderId);
    }
}
