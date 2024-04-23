package com.example.shishangsujian.savePhoto;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class savePhotoController {
    @Autowired
    private savePhotoService service;

    @Autowired
    private HttpSession session;
    @GetMapping ("/getlist")
    public List<savePhotoModel> showlist(){
        return service.showList(session);
    }

    @GetMapping("/getpath")
    public String getPath(@RequestParam String name,@RequestParam int sign){
        return service.getPath(session,name,sign);
    }

    @PostMapping("/savephoto")
    public void savephoto(@RequestParam int sign, @RequestParam String name, @RequestParam String newname, MultipartFile file){
        service.savePhoto(session,sign,name,newname,file);
    }
}
