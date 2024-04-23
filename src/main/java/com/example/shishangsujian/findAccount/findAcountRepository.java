package com.example.shishangsujian.findAccount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface findAcountRepository extends JpaRepository<findAccountModel,Long> {
    findAccountModel findByMobile(String mobile);

    findAccountModel findById(long id);
}
