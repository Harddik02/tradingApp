package com.example.portfolioapplication.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockDetailResponseDTO {
    String stockId;
    String stockName;
    Double openPrice;
    Double highPrice;
    Double lowPrice;
    Double closePrice;
    Double settlementPrice;
}
