package com.example.portfolioapplication.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockUpdateResponseDTO {
    String message;
}
