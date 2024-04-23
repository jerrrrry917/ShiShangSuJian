package com.example.shishangsujian.getuserid;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/getuserid")
public class getuseridController {
    @Autowired
    private getuseridService getuseridService;
    @Autowired
    private HttpSession session;//获取unionid,保存id

    @GetMapping("")
    public long getid(){
        String testid= session.getAttribute("unionid").toString();
        getuseridService.compareid(testid,session);
        return (Long)session.getAttribute("Id");
    }
}
