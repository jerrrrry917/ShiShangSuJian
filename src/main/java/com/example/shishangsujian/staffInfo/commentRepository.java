package com.example.shishangsujian.staffInfo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface commentRepository extends JpaRepository<staffCommentModel,Long> {
    List<staffCommentModel> findByStaffIdAndQuestion(String staffId,String question);
}
