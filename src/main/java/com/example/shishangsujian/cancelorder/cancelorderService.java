package com.example.shishangsujian.cancelorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class cancelorderService {
    @Autowired
    private cancelorderRepository repository;

    public void cancelOrder(long orderId){
        repository.deleteById(orderId);
    }
}
