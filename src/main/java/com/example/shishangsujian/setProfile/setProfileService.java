package com.example.shishangsujian.setProfile;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class setProfileService {
    @Autowired
    private setProfileRepository repository;

    private static final String UPLOAD_DIR = "wxdata/static/profile/";
    private static final String SAVE_DIR = "profile/";
    public setProfileModel getprofile(HttpSession session){
        int memberId = Math.toIntExact((Long) session.getAttribute("Id"));
        setProfileModel user=repository.findByMemberId(memberId);
        return user;
    }

    public void setnickname(HttpSession session,String nickname){
        int memberId = Math.toIntExact((Long) session.getAttribute("Id"));
        if(repository.findByMemberId(memberId)!=null){
            setProfileModel olduser=repository.findByMemberId(memberId);
            olduser.setNickname(nickname);
            repository.save(olduser);
        }

        else{
            setProfileModel newuser=new setProfileModel();
            newuser.setNickname(nickname);
            newuser.setMemberId(memberId);
            repository.save(newuser);
        }
    }

    public void setphoto(HttpSession session, MultipartFile file){
        String fileName = file.getOriginalFilename();
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();  // 创建目录和任何必要的父目录
            System.out.println("创建成功");
        }

        // 添加时间戳前缀防止文件名冲突
        String newFileName = System.currentTimeMillis() + "_" + fileName;

        Path path = Paths.get(UPLOAD_DIR + newFileName);
        int memberId = Math.toIntExact((Long) session.getAttribute("Id"));

        try {
            Files.copy(file.getInputStream(), path);

            if(repository.findByMemberId(memberId)!=null){
                setProfileModel olduser=repository.findByMemberId(memberId);
                olduser.setPhoto(SAVE_DIR+newFileName);
                repository.save(olduser);
            }

            else{
                setProfileModel newuser=new setProfileModel();
                newuser.setPhoto(SAVE_DIR+newFileName);
                newuser.setNickname("微信用户");
                newuser.setMemberId(memberId);
                repository.save(newuser);
            }
        }

        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
