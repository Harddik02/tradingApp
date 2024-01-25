package com.example.portfolioapplication.controller;

import com.example.portfolioapplication.dto.PortfolioResponseDTO;
import com.example.portfolioapplication.entity.HoldingEntity;
import com.example.portfolioapplication.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class UserPortfolioController {
    @Autowired
    StockService stockService;

    @GetMapping(value="/user_portfolio/{user_id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    PortfolioResponseDTO getUserPortfolio(@PathVariable("user_id") Integer userId) {
        List<HoldingEntity> holdings = stockService.fetchUserHoldings(userId);
        return stockService.calculateUserPortfolio(holdings);
    }
}
