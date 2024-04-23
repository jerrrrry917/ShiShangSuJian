package com.example.shishangsujian.stafflevel;

import com.example.shishangsujian.createcomment.membergrade.*;
import com.example.shishangsujian.getstafflist.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class stafflevelService {
    @Autowired
    private getstafflistRepository getstafflistRepository;

    @Autowired
    private membergradeRepository membergradeRepository;

    public void changeStaffLevel(int orderId){
        membergradeModel order=membergradeRepository.findByOrdersId(orderId);
        getstafflistModel staff=getstafflistRepository.findById(order.getStaffId());

        double level=staff.getLevel();
        int point=order.getSumValue();

        if(level==100||level<=60)
            return;

        if(point>0)
            level+=0.5;

        else if(point<0)
            level-=2;

        staff.setLevel(level);
        getstafflistRepository.save(staff);
    }
}
