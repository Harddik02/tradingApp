package com.example.portfolioapplication.controller;

import com.example.portfolioapplication.dto.TradeResponseDTO;
import com.example.portfolioapplication.service.TradeService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class TradeController {

    @Autowired
    TradeService tradeService;

    @PostMapping(value = "/trade", produces = MediaType.APPLICATION_JSON_VALUE)
    TradeResponseDTO processTransaction(@RequestParam("UserId") Integer userId,
                                        @RequestParam("TradeType") String tradeType,
                                        @RequestParam("Quantity") Integer quantity,
                                        @RequestParam("StockId") String stockId) {

        String[] result = tradeService.performTransaction(userId, tradeType, quantity, stockId);
        return new TradeResponseDTO(result[0], result[1]);
    }
}
