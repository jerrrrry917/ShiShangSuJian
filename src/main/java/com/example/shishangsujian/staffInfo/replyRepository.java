package com.example.shishangsujian.staffInfo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface replyRepository extends JpaRepository<staffReplyModel,Long> {
    List<staffReplyModel> findByStaffId(int staffId);

    List<staffReplyModel> findByStaffIdAndCreatetimeBetween(Integer staffId, Date startDate, Date endDate);
}
