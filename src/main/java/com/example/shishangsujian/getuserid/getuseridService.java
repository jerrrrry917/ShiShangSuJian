package com.example.shishangsujian.getuserid;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class getuseridService {

    @Autowired
    private getuseridRepository repository;

    public void compareid(String testid, HttpSession session){//testid为测试unionid，检测数据库中是否有相同unionid
        getuseridModel user=repository.findByUnionid(testid);
        if(user != null){
            session.setAttribute("Id",user.getId());
            user.setOpenid(session.getAttribute("openid").toString());
            repository.save(user);
        }

        else{
            System.out.println("创建新用户");
            getuseridModel newUser = new getuseridModel();

            Long maxId=repository.findMaxId();
            newUser.setId(maxId+1);
            newUser.setOpenid(session.getAttribute("openid").toString());
            newUser.setUnionid(testid);
            repository.save(newUser); // 保存新用户到数据库
            session.setAttribute("Id", newUser.getId()); // 将新用户的ID保存到session中
        }
    }
}
