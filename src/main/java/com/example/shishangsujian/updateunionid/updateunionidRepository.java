package com.example.shishangsujian.updateunionid;
import com.example.shishangsujian.transopenid.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface updateunionidRepository extends JpaRepository<transopenidModel,Long> {
    List<transopenidModel> findAll();

}
