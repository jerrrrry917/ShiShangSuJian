package com.example.shishangsujian.setProfile;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class setProfileController {
    @Autowired
    private setProfileService service;

    @Autowired
    private HttpSession session;

    @GetMapping("/getprofile")
    public setProfileModel getprofile(){
        return service.getprofile(session);
    }

    @PostMapping("/setphoto")
    public void setphoto(MultipartFile file){
        service.setphoto(session,file);
    }

    @GetMapping("/setnickname")
    public void setnickname(@RequestParam String nickname){
        service.setnickname(session,nickname);
    }
}
