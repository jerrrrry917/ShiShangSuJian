package com.example.shishangsujian.stafforders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class staffordersService {
    @Autowired
    private staffordersRepository repository;

    public List<staffordersModel> getstafforders(String staffId,String status){
        return repository.findByStaffIdAndStatus(staffId,status);
    }

    public void reneworder1(Long id) {
        Optional<staffordersModel> optionalModel = repository.findById(id);
        if (optionalModel.isPresent()) {
            staffordersModel model = optionalModel.get();
            model.setStatus("1");
            repository.save(model);
        } else {
            throw new RuntimeException("Order not found with id: " + id);
        }
    }

    public void reneworder2(Long id) {
        Optional<staffordersModel> optionalModel = repository.findById(id);
        if (optionalModel.isPresent()) {
            staffordersModel model = optionalModel.get();
            model.setStatus("2");
            repository.save(model);
        } else {
            throw new RuntimeException("Order not found with id: " + id);
        }
    }

    public void reneworder3(Long id) {
        Optional<staffordersModel> optionalModel = repository.findById(id);
        if (optionalModel.isPresent()) {
            staffordersModel model = optionalModel.get();
            LocalDateTime now = LocalDateTime.now();
            model.setPay_time(now);
            model.setStatus("3");
            model.setType(0);
            repository.save(model);
        } else {
            throw new RuntimeException("Order not found with id: " + id);
        }
    }

    public void reneworderCash(Long id){
        Optional<staffordersModel> optionalModel = repository.findById(id);
        if (optionalModel.isPresent()) {
            staffordersModel model = optionalModel.get();
            LocalDateTime now = LocalDateTime.now();
            model.setPay_time(now);
            model.setStatus("3");
            model.setType(1);
            repository.save(model);
        } else {
            throw new RuntimeException("Order not found with id: " + id);
        }
    }
}
