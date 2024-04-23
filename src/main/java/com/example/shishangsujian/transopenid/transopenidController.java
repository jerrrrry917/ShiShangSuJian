package com.example.shishangsujian.transopenid;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class transopenidController {
    @Autowired
    private transopenidService transopenid;

    @Autowired
    private HttpSession session;
    @RequestMapping("/getunionid")
    public void setunionid(@RequestParam String testid){
        transopenid.getunionid2(testid,session);
    }

    @RequestMapping("/getaccesstoken")
    public void getaccessToken(){
        transopenid.getAccessToken(session);
    }

    @RequestMapping("/getalljson")
    public  ResponseEntity<String> getall(@RequestParam String testid){
        String jsonResponse = transopenid.getunionid3(testid, session);
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }

}
