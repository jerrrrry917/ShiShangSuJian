package com.example.shishangsujian.WXpay;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pay")
public class WXpayController {

    @Autowired
    private WXpayService wxpayService;

    @PostMapping("/createprepay")
    public Object createprepay(@RequestParam double price, @RequestParam String name, @RequestParam long orderid, HttpSession session){
        return wxpayService.createPrepayOrder(price,name,orderid,session);
    }

    @PostMapping("/notification")
    public ResponseEntity<String> handleWXNotification(@RequestBody String xmlData) {
        String response = wxpayService.handlePaymentNotification(xmlData);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(response);
    }
}
