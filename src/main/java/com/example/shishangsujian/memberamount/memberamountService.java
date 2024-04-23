package com.example.shishangsujian.memberamount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class memberamountService {
    @Autowired
    private memberamountRepository repository;

    public void saveMemberamount(memberamountModel memberamount){
        if(memberamount.getOptType().equals("+")){
            memberamount.setType("充值");
            if(memberamount.getMoney()==5){
                memberamount.setRemark("注册赠送红包");
            }

            else
                memberamount.setRemark("微信充值，增加资金");
        }

        else if(memberamount.getOptType().equals("-")) {
            memberamount.setType("抵扣");
            memberamount.setRemark("抵扣，扣减资金");
        }

        else
            memberamount.setType(null);

        repository.save(memberamount);
    }
}
