package com.example.portfolioapplication.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Holdings {
    String stockName;
    String stockId;
    Integer quantity;
    Double buyPrice;
    Double currentPrice;
    Double pnl;
}
