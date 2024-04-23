package com.example.shishangsujian.stafflevel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/setlevel")
public class stafflevelController {
    @Autowired
    private stafflevelService service;

    @GetMapping("")
    public void setLevel(@RequestParam int orderId){
        service.changeStaffLevel(orderId);
    }
}
