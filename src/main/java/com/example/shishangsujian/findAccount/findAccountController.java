package com.example.shishangsujian.findAccount;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/findAccount")
public class findAccountController {
    @Autowired
    private findAccountService service;

    @PostMapping("")
    public ResponseEntity<String> checkaccount(@RequestParam String mobile, HttpSession session){
        System.out.println("Received mobile: " + mobile);
        return service.findAccount(mobile, session);
    }
}
