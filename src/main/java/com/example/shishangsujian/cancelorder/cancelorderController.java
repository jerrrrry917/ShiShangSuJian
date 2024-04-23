package com.example.shishangsujian.cancelorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cancelorder")
public class cancelorderController {

    @Autowired
    cancelorderService service;

    @GetMapping("")
    public ResponseEntity<?> cancelorder(@RequestParam long orderId) {
        try {
            service.cancelOrder(orderId);
            return ResponseEntity.ok("Order with ID " + orderId + " has been successfully cancelled.");
        } catch (Exception e) {
            // 异常处理，如订单不存在或删除失败
            return ResponseEntity.badRequest().body("Error cancelling order with ID " + orderId + ": " + e.getMessage());
        }
    }
}
