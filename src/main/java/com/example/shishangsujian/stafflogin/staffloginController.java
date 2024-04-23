package com.example.shishangsujian.stafflogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/stafflogin")
public class staffloginController {
    @Autowired
    private staffloginService service;

    @Autowired
    private staffloginRepository repository;
    @PostMapping("")
    public ResponseEntity<?> Login(@RequestParam String mobile,@RequestParam String password){
        staffloginModel staff = service.login(mobile, password);
        if (staff != null) {
            return ResponseEntity.ok(staff);
        } else {
            staffloginModel foundByMobile = repository.findByMobile(mobile);
            if (foundByMobile == null) {
                return ResponseEntity.status(404).body("User not found");
            } else {
                return ResponseEntity.status(401).body("Unauthorized");
            }
        }
    }

    // 更新密码方法
    @PostMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestParam String mobile, @RequestParam String oldPassword, @RequestParam String newPassword) {
        boolean updateSuccessful = service.updatePassword(mobile, oldPassword, newPassword);

        if (updateSuccessful) {
            return ResponseEntity.ok("Password updated successfully");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials or password not updated");
        }
    }
}
