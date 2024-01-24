package com.example.portfolioapplication.service;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CsvTemplate {
    @CsvBindByName (column ="ISIN")
    private String id;
    @CsvBindByName(column="SYMBOL")
    private String name;
    @CsvBindByName(column="OPEN")
    private Double openPrice;
    @CsvBindByName(column="CLOSE")
    private Double closePrice;
    @CsvBindByName(column="HIGH")
    private Double highPrice;
    @CsvBindByName(column="LOW")
    private Double lowPrice;
    @CsvBindByName(column="PREVCLOSE")
    private Double settlementPrice;


}
