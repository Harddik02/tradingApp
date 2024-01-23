package com.example.portfolioapplication.repository;

import com.example.portfolioapplication.entity.StockEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {

    StockEntity findByStockId(String stockId);
}
