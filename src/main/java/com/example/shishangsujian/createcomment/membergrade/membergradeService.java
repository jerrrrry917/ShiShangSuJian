package com.example.shishangsujian.createcomment.membergrade;
import com.example.shishangsujian.NormalOrderModel;
import com.example.shishangsujian.NormalOrderRepository;
import com.example.shishangsujian.UserModel;
import com.example.shishangsujian.UserRepository;
import com.example.shishangsujian.createorders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class membergradeService {
    @Autowired
    membergradeRepository membergradeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    NormalOrderRepository normalOrderRepository;
    @Autowired
    createordersRepository createordersRepository;
    public void countScore(String serial ,Date createtime,String comment){
        membergradeModel model=new membergradeModel();
        createordersModel order=createordersRepository.findBySerial(serial);

        String memberIdString=Integer.toString(order.getMemberId());
        model.setMemberId(memberIdString);

        model.setStaffId(Integer.parseInt(order.getStaffId()));

        model.setOrdersId((int)order.getId());

        model.setCreateTime(createtime);

        model.setSumValue(-999);

        model.setEdits("0");

        model.setShopId(order.getShopId());

        model.setDictionaryId(2);

        model.setOptName(order.getOptName());

        //TODO:设置电话

        model.setSerial(serial);

        model.setAnswers(comment);
        membergradeRepository.save(model);
    }

    public void setbenefit(long orderId){
        NormalOrderModel order=normalOrderRepository.findById(orderId);

        UserModel user=userRepository.findById(order.getMemberId());
        user.setBenefit(1);
        userRepository.save(user);
    }
}
