package com.example.shishangsujian.stafflogin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface staffloginRepository extends JpaRepository<staffloginModel,Long> {
    staffloginModel findByMobile(String mobile);
}
