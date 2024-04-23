package com.example.shishangsujian.savePhoto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface savePhotoRepository extends JpaRepository<savePhotoModel,Long> {
    savePhotoModel findByMemberIdAndName(int memberId,String name);

    List<savePhotoModel> findByMemberId(int memberId);
}
