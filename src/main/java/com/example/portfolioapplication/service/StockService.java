package com.example.portfolioapplication.service;

import com.example.portfolioapplication.dto.Holdings;
import com.example.portfolioapplication.dto.PortfolioResponseDTO;
import com.example.portfolioapplication.entity.HoldingEntity;
import com.example.portfolioapplication.entity.StockEntity;
import com.example.portfolioapplication.repository.HoldingRepository;
import com.example.portfolioapplication.repository.StockRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private HoldingRepository holdingRepository;

    public StockEntity fetchStockDetails(String stockId) {
        StockEntity stock = stockRepository.findByStockId(stockId);
        return stock;
    }

    public List<HoldingEntity> fetchUserHoldings(Integer userId) {
        List<HoldingEntity> holdings = holdingRepository.findByUserId(userId);
        return holdings;
    }

    public PortfolioResponseDTO calculateUserPortfolio(@NotNull List<HoldingEntity> holdings) {
        PortfolioResponseDTO userPortfolio = new PortfolioResponseDTO();
        userPortfolio.holdings = new ArrayList<Holdings>();
        Double totalPortfolioHolding = 0.0;
        Double totalBuyPrice = 0.0;
        Double totalProfitAndLoss = 0.0;
        for(HoldingEntity holding : holdings) {
            Holdings stockHolding = new Holdings();
            stockHolding.setStockId(holding.getStockId());
            stockHolding.setStockName(fetchStockDetails(holding.getStockId()).getStockName());
            stockHolding.setQuantity(holding.getQuantity());
            stockHolding.setBuyPrice(holding.getBuyPrice());
            totalBuyPrice += holding.getBuyPrice();
            stockHolding.setCurrentPrice(fetchStockDetails(holding.getStockId()).getClosePrice());
            stockHolding.setPnl(stockHolding.getCurrentPrice() - stockHolding.getBuyPrice());
            userPortfolio.holdings.add(stockHolding);
            totalPortfolioHolding += stockHolding.getQuantity() * stockHolding.getCurrentPrice();
            totalProfitAndLoss += (stockHolding.getCurrentPrice() - stockHolding.getBuyPrice()) * stockHolding.getQuantity();
        }
        userPortfolio.setTotalBuyPrice(totalBuyPrice);
        userPortfolio.setTotalPortfolioHolding(totalPortfolioHolding);
        userPortfolio.setTotalBuyPrice(totalBuyPrice);
        userPortfolio.setTotalProfitAndLoss(totalProfitAndLoss);
        userPortfolio.setTotalPnlPercent(totalProfitAndLoss / totalBuyPrice);
        return userPortfolio;
    }


    public void downloadAndLoadBhavcopy(MultipartFile bhavfile) throws IOException {

        Set<StockEntity> stock=parseCsv(bhavfile);
        stockRepository.saveAll(stock);

    }

    private Set<StockEntity> parseCsv(MultipartFile bhavfile) throws IOException{
        try(Reader reader=new BufferedReader(new InputStreamReader(bhavfile.getInputStream())))
        {
            HeaderColumnNameMappingStrategy<CsvTemplate> convert=new HeaderColumnNameMappingStrategy<>();
            convert.setType(CsvTemplate.class);
            CsvToBean<CsvTemplate> csvToBean=new CsvToBeanBuilder<CsvTemplate>(reader)
                    .withMappingStrategy(convert)
                    .withIgnoreEmptyLine(true)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean
                    .parse()
                    .stream()
                    .map(csvLine-> new StockEntity(csvLine.getId(),csvLine.getName(),csvLine.getOpenPrice(),csvLine.getHighPrice(),csvLine.getLowPrice(),csvLine.getClosePrice(),csvLine.getSettlementPrice()))
                    .collect(Collectors.toSet());
        }
    }


}
