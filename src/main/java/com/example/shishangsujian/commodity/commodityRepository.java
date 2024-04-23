package com.example.shishangsujian.commodity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface commodityRepository extends JpaRepository<commodityModel,Integer> {
    commodityModel findById(int id);
}
