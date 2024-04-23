package com.example.shishangsujian.updateunionid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.processing.Generated;

@RestController
@RequestMapping("/api/updateunionid")
public class updateunionidController {
    @Autowired
    private updateunionidService updateunionid;

    @GetMapping("")
    public String updateAllUnionIds() {
        try {
            updateunionid.updateAllUnionIds();
            return "Union IDs updated successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating Union IDs: " + e.getMessage();
        }
    }
}
