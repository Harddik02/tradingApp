package com.example.portfolioapplication.repository;

import com.example.portfolioapplication.dto.Holdings;
import com.example.portfolioapplication.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserId(Integer userId);
}
