package com.example.shishangsujian.membercomplaint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/membercomplaint")
public class membercomplaintController {

    @Autowired
    private com.example.shishangsujian.membercomplaint.membercomplaintService membercomplaintService;

    @PostMapping("/savemembercomplaint")
    public ResponseEntity<String> creatMemberamount(@RequestBody membercomplaintModel membercomplaint) {
        try {
            membercomplaintService.saveMembercomplaint(membercomplaint);
            return new ResponseEntity<>("membercomplaint created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating membercomplaint: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
