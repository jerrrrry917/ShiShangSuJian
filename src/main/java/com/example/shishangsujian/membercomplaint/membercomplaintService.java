package com.example.shishangsujian.membercomplaint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class membercomplaintService {
    @Autowired
    private membercomplaintRepository repository;

    public void saveMembercomplaint(membercomplaintModel membercomplaint){
        repository.save(membercomplaint);
    }
}
