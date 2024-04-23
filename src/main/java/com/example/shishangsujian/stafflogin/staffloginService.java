package com.example.shishangsujian.stafflogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class staffloginService {
    @Autowired
    private staffloginRepository repository;

    public staffloginModel login(String mobile, String password) {
        staffloginModel user = repository.findByMobile(mobile);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    public boolean updatePassword(String mobile, String oldPassword, String newPassword) {
        staffloginModel user = login(mobile, oldPassword); // 使用现有的登录方法来验证旧密码

        // 如果用户存在并且旧密码正确
        if (user != null) {
            user.setPassword(newPassword); // 设置新密码
            repository.save(user); // 保存更新后的用户信息
            return true; // 返回成功
        }

        return false; // 如果验证失败，返回失败
    }
}


