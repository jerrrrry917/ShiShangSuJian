package com.example.shishangsujian;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel,Long> {
    UserModel findById(long id);
}
