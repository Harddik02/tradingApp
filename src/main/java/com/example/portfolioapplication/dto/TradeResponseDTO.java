package com.example.portfolioapplication.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TradeResponseDTO {
    public String tradeStatus;
  public String transactionMessage;



    @Override
    public String toString() {
        return "TradeResponseDTO{" +
                "tradeStatus='" + tradeStatus + '\'' +
                ", transactionMessage='" + transactionMessage + '\'' +
                '}';
    }

    public TradeResponseDTO(String tradeStatus, String transactionMessage) {
        this.tradeStatus = tradeStatus;
        this.transactionMessage = transactionMessage;
    }
}
