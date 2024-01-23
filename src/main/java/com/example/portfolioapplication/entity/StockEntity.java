package com.example.portfolioapplication.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "stocks")
public class StockEntity {
    @Id
    String stockId;
    String stockName;
    Double openPrice;
    Double highPrice;
    Double lowPrice;
    Double closePrice;
    Double settlementPrice;
}
