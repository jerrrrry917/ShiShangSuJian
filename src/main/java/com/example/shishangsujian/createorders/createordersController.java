package com.example.shishangsujian.createorders;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/createorders")
public class createordersController {
    @Autowired
    createordersService service;

    @PostMapping("")
    public void createorders(@RequestBody createordersModel order, HttpSession session){
        service.createorder(order,session);
    }
}
