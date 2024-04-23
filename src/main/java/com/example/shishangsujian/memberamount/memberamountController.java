package com.example.shishangsujian.memberamount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/memberamount")
public class memberamountController {

    @Autowired
    private com.example.shishangsujian.memberamount.memberamountService memberamountService;

    @PostMapping("/savememberamount")
    public ResponseEntity<String> creatMemberamount(@RequestBody memberamountModel memberamount) {
        try {
            memberamountService.saveMemberamount(memberamount);
            return new ResponseEntity<>("memberamount created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating memberamount: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
