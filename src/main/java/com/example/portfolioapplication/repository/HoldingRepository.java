package com.example.portfolioapplication.repository;

import com.example.portfolioapplication.entity.HoldingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoldingRepository extends JpaRepository<HoldingEntity, Long> {

    HoldingEntity findByUserIdAndStockId(Integer userId, String stockId);

    List<HoldingEntity> findByUserId(Integer userId);

}
