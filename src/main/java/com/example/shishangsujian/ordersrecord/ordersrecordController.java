package com.example.shishangsujian.ordersrecord;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/getorders")
public class ordersrecordController {
    @Autowired
    private  ordersrecordService ordersrecordService;

    @Autowired
    private HttpSession session;
    @GetMapping("")
    public List<ordersrecordModel> sendorders(@RequestParam String status){
        return ordersrecordService.getorders(session,status);
    }
}
