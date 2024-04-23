package com.example.shishangsujian.login;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.shishangsujian.getuserid.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/login")
public class loginController {
    @Autowired
    private loginService loginService;

    @Autowired
    private getuseridService getuseridService;
    @Autowired
    private HttpSession session;
    @GetMapping("")
    public void login(@RequestParam String code) {
        loginService.getOpenIdUnionIdAndSessionKey(code,session);
    }
}
