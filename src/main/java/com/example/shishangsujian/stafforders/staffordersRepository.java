package com.example.shishangsujian.stafforders;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface staffordersRepository extends JpaRepository<staffordersModel,Long> {
    List<staffordersModel> findByStaffIdAndStatus(String staffId,String status);
}
