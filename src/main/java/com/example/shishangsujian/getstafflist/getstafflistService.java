package com.example.shishangsujian.getstafflist;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class getstafflistService {
    @Autowired
    private getstafflistRepository repository;

    public List<getstafflistModel> getstafflist(String shopid){
        return repository.findByShopId(shopid);
    }

    public getstafflistModel getstaffById(long id){
        return repository.findById(id);
    }

    public void startorstopwork(int staffId){
        getstafflistModel staff=repository.findById(staffId);
        if(staff.getStates()==0)
            staff.setStates(1);

        else if(staff.getStates()==1)
            staff.setStates(0);

        repository.save(staff);
    }
}