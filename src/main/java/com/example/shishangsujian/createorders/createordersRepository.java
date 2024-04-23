package com.example.shishangsujian.createorders;

import org.springframework.data.jpa.repository.JpaRepository;

public interface createordersRepository extends JpaRepository<createordersModel,Long> {
    createordersModel findBySerial(String serial);
}
