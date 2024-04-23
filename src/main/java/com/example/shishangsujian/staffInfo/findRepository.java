package com.example.shishangsujian.staffInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface findRepository extends JpaRepository<staffOrdersInfoModel,Long> {
    @Query("SELECT o FROM staffOrdersInfoModel o WHERE o.createtime BETWEEN CONCAT(:startDate, ' 00:00:00') AND CONCAT(:endDate, ' 23:59:59') AND o.status = :status AND o.staffId = :staffId")
    List<staffOrdersInfoModel> findByCreateDateBetweenAndStatusAndStaffId(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("status") String status, @Param("staffId") String staffId);

    @Query("SELECT o FROM staffOrdersInfoModel o WHERE o.createtime BETWEEN CONCAT(:date, ' 00:00:00') AND CONCAT(:date, ' 23:59:59') AND o.status = :status AND o.staffId = :staffId")
    List<staffOrdersInfoModel> findByCreateDateAndStatusAndStaffId(@Param("date") String date, @Param("status") String status, @Param("staffId") String staffId);

    List<staffOrdersInfoModel> findByStatus(String status);

}
