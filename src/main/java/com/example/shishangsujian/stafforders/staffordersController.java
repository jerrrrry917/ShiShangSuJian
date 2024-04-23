package com.example.shishangsujian.stafforders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/stafforders")
public class staffordersController {
    @Autowired
    private staffordersService service;

    @GetMapping("")
    public List<staffordersModel> getorders(@RequestParam String staffId,@RequestParam String status){
        return service.getstafforders(staffId,status);
    }

    @PostMapping("/renew")
    public void renew1(@RequestParam Long id,@RequestParam String status){
        switch (status) {
            case "1" -> service.reneworder1(id);
            case "2" -> service.reneworder2(id);
            case "3" -> service.reneworder3(id);
            case "4" -> service.reneworderCash(id);
        }
    }
}
