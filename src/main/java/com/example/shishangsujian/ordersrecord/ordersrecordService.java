package com.example.shishangsujian.ordersrecord;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ordersrecordService {
    @Autowired
    private ordersrecordRepository repository;

    public List<ordersrecordModel> getorders(HttpSession session,String status) {
        Object idObj = session.getAttribute("Id");
        if (idObj != null) {
            int check = ((Long) idObj).intValue();
            List<ordersrecordModel> ordersrecord;
            if(!status.equals("3")){
                ordersrecord = repository.findByMemberIdAndStatus(check, status);
            }

            else {
                ordersrecord = repository.findByMemberIdAndStatusOrMemberIdAndStatus(check, "3", check, "4");
            }
            return ordersrecord;
        } else {
            System.out.println("error");
            return null;
        }
    }
}