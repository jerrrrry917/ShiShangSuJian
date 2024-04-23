package com.example.shishangsujian.requestMessage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface requestRepository extends JpaRepository<requestModel,Long> {
    requestModel findById(long id);
}
