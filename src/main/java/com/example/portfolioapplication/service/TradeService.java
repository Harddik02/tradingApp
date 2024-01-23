package com.example.portfolioapplication.service;

import com.example.portfolioapplication.entity.HoldingEntity;
import com.example.portfolioapplication.entity.StockEntity;
import com.example.portfolioapplication.entity.UserEntity;
import com.example.portfolioapplication.repository.HoldingRepository;
import com.example.portfolioapplication.repository.StockRepository;
import com.example.portfolioapplication.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Setter
@Getter
@NoArgsConstructor
@Service
@Transactional
public class TradeService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HoldingRepository holdingRepository;

    @Autowired
    private StockRepository stockRepository;

    public String[] performTransaction(Integer userId, String tradeType, Integer quantity, String stockId) {
        HoldingEntity holding = holdingRepository.findByUserIdAndStockId(userId, stockId);
        UserEntity user = userRepository.findByUserId(userId);
        if(Objects.isNull(user)) {
            user = new UserEntity();
            user.setUserId(userId);
            userRepository.save(user);
        }
        HoldingEntity newHolding = new HoldingEntity();
        newHolding.setUserId(userId);
        newHolding.setStockId(stockId);
        if(Objects.isNull(holding)) {
            newHolding.setBuyPrice(0.0);
            newHolding.setQuantity(0);
        } else {
            newHolding.setBuyPrice(holding.getBuyPrice());
            newHolding.setQuantity(holding.getQuantity());
        }
        Integer currentStockQuantity = newHolding.getQuantity();
        Double stockBuyPrice = newHolding.getBuyPrice();
        StockEntity stock = stockRepository.findByStockId(stockId);
        String[] result = {"Success", "Order executed successfully!"};
        if(tradeType.equals("buy")) {
            stockBuyPrice += quantity * stock.getClosePrice();
            currentStockQuantity += quantity;
            newHolding.setBuyPrice(stockBuyPrice);
            newHolding.setQuantity(currentStockQuantity);
            holdingRepository.save(newHolding);
        } else if(currentStockQuantity >= quantity) {
            stockBuyPrice -= quantity * stock.getClosePrice();
            currentStockQuantity -= quantity;
            newHolding.setBuyPrice(stockBuyPrice);
            newHolding.setQuantity(currentStockQuantity);
            holdingRepository.save(newHolding);
        } else {
            result = new String[]{"Failure", "Not enough shares to sell!"};
        }
        if(!Objects.isNull(holding)) {
            holdingRepository.delete(holding);
        }
        return result;
    }
}
