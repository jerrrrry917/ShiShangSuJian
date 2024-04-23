package com.example.shishangsujian.findAccount;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class findAccountService {
    @Autowired
    findAcountRepository repository;

    public ResponseEntity<String> findAccount(String mobile, HttpSession session){
        findAccountModel oldAccount = repository.findByMobile(mobile);
        if(oldAccount == null){
            return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
        }

        findAccountModel nowAccount = repository.findById((long) session.getAttribute("Id"));
        if(nowAccount.getOpenid() == null){
            repository.delete(nowAccount);
            oldAccount.setUnionid(nowAccount.getUnionid());
            repository.save(oldAccount);
            return new ResponseEntity<>("Account found successfully, please restart the app", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Current account is already an old user. Please use the same WeChat account as the old account to search for an account", HttpStatus.BAD_REQUEST);
        }
    }
}
