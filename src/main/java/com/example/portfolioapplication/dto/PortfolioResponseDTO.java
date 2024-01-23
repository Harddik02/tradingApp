package com.example.portfolioapplication.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PortfolioResponseDTO {
    public List<Holdings> holdings;
    public Double totalPortfolioHolding;
    public Double totalBuyPrice;
    public Double totalProfitAndLoss;
    public Double totalPnlPercent;
}
