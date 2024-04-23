package com.example.shishangsujian.judgeBenefit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/judgeBenefit")
public class judgeBenefitController {
    @Autowired
    private judgeBenefitService service;

    @GetMapping("")
    public int getBenefits(@RequestParam String serial){
        return service.findBenefit(serial);
    }
}
