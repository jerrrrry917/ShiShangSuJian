package com.example.shishangsujian.createcomment;

import com.example.shishangsujian.createcomment.membergrade.membergradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@RequestMapping("/api/createcomment")
public class createcommentController {

    @Autowired
    createcommentService createcommentService;

    @Autowired
    membergradeService membergradeService;

    @GetMapping("/create")
    public void createcomment(@RequestParam String serial, @RequestParam String questionId, @RequestParam String answerId){
        createcommentService.createcomment(serial,questionId,answerId);
        createcommentService.setStatus(serial);
    }

    @GetMapping("/getScore")
    public void countScore(@RequestParam String serial, @RequestParam long orderId, @RequestParam String createtimeStr , @RequestParam String comment){
        // 解析字符串为LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime localDateTime = LocalDateTime.parse(createtimeStr, formatter);

        // 将LocalDateTime转换为Date
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        Date createtime = Date.from(zonedDateTime.toInstant());

        // 使用Date对象调用服务
        membergradeService.countScore(serial, createtime , comment);
        membergradeService.setbenefit(orderId);
    }
}
