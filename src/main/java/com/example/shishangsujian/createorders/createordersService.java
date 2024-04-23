package com.example.shishangsujian.createorders;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class createordersService {
    @Autowired
    private createordersRepository repository;

    //生成订单号函数
    private String generateSerial(String createTimeString, String staffId) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateSdf = new SimpleDateFormat("MMdd");
        SimpleDateFormat timeSdf = new SimpleDateFormat("HHmm0ss");

        try {
            Date createTime = dateFormat.parse(createTimeString);
            String datePart = dateSdf.format(createTime);
            String timePart = timeSdf.format(createTime);
            return datePart + timePart + staffId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void createorder(createordersModel order,HttpSession session){
        createordersModel newOrder=new createordersModel();
        Long Id= (Long) session.getAttribute("Id");
        newOrder.setMemberId(Id.intValue());
        newOrder.setStaffId(order.getStaffId());
        newOrder.setOptName(order.getOptName());
        newOrder.setShopId(order.getShopId());
        newOrder.setMoney(order.getMoney());
        newOrder.setStatus(order.getStatus());
        newOrder.setCreatetime(order.getCreatetime());

        newOrder.setProductClassId(order.getProductClassId());

        String ShopId= order.getShopId();
        if(ShopId.equals("6"))
            newOrder.setShopName("海岸大厦店");

        else if(ShopId.equals("8"))
            newOrder.setShopName("蛇口万科公馆店");

        newOrder.setSerial(generateSerial(order.getCreatetime(),order.getStaffId()));

        repository.save(newOrder);
    }
}
