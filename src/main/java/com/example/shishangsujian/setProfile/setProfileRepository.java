package com.example.shishangsujian.setProfile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface setProfileRepository extends JpaRepository<setProfileModel,Long> {
    setProfileModel findByMemberId(int memberId);
}
