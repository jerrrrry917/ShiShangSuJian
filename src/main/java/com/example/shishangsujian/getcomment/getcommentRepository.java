package com.example.shishangsujian.getcomment;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface getcommentRepository extends JpaRepository<getcommentModel,Long> {
    List<getcommentModel> findByShopId(String shopId, Sort sort);
}
