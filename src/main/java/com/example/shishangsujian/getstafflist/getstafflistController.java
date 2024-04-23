package com.example.shishangsujian.getstafflist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/getstafflist")
public class getstafflistController {
    @Autowired
    getstafflistService service;

    @GetMapping("")
    public List<getstafflistModel> getstaff(@RequestParam String shopid){
        return service.getstafflist(shopid);
    }

    @GetMapping("/byId")
    public getstafflistModel getstaffById(@RequestParam long id){
        return service.getstaffById(id);
    }

    @GetMapping("/workstates")
    public void startWork(@RequestParam int staffId){
        service.startorstopwork(staffId);
    }

}
