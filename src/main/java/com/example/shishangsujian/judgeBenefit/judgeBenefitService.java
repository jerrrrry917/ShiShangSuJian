package com.example.shishangsujian.judgeBenefit;

import com.example.shishangsujian.NormalOrderModel;
import com.example.shishangsujian.NormalOrderRepository;
import com.example.shishangsujian.UserModel;
import com.example.shishangsujian.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class judgeBenefitService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NormalOrderRepository normalOrderRepository;

    public int findBenefit(String serial){
        NormalOrderModel order=normalOrderRepository.findBySerial(serial);

        UserModel user=userRepository.findById(order.getMemberId());

        Integer benefit=user.getBenefit();

        if(benefit==null)
            return 0;

        else
            return benefit;
    }
}
