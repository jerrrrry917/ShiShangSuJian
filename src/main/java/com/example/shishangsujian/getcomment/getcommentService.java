package com.example.shishangsujian.getcomment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class getcommentService {
    @Autowired
    private getcommentRepository repository;

    public List<getcommentModel> getComments(String shopId){
        Sort sort = Sort.by(Sort.Order.desc("sumValue"), Sort.Order.desc("createtime"));
        return repository.findByShopId(shopId,sort);
    }
}
