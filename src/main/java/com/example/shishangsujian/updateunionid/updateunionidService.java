package com.example.shishangsujian.updateunionid;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.shishangsujian.transopenid.*;

import java.util.List;
import java.util.Objects;

@Service
public class updateunionidService {

    @Autowired
    private updateunionidRepository repository;

    @Autowired
    private transopenidService service;

    @Autowired
    HttpSession session;
    public void updateAllUnionIds() {
        List<transopenidModel> allTransOpenid=repository.findAll();

        allTransOpenid.stream()
                .filter(transopenidModel -> transopenidModel.getUnionid() == null)
                .map(transopenidModel::getOpenid)
                .filter(Objects::nonNull)
                .distinct()
                .forEach(openid -> service.getunionid2(openid, session));
    }
}
