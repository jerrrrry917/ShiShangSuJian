package com.example.shishangsujian.commodity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface commodityPayRepository extends JpaRepository<commodityPayModel,Integer> {
    List<commodityPayModel> findByStaffId(int StaffId);
}
