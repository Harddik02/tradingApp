package com.example.portfolioapplication.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Holdings {
    private String stockName;
    private  String stockId;
    private Integer quantity;
    private Double buyPrice;
    private Double currentPrice;
    private Double pnl;
}
