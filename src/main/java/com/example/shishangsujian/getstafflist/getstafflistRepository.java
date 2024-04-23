package com.example.shishangsujian.getstafflist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface getstafflistRepository extends JpaRepository<getstafflistModel,Long> {
    List<getstafflistModel> findByShopId(String shopid);

    getstafflistModel findById(long id);
}
