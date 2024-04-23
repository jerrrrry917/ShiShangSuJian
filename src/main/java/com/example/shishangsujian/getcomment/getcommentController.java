package com.example.shishangsujian.getcomment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/getcomments")
public class getcommentController {
    @Autowired
    private getcommentService service;

    @GetMapping("")
    public List<getcommentModel> getComments(@RequestParam String shopId){
        return service.getComments(shopId);
    }
}
