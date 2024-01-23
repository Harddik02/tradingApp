package com.example.portfolioapplication.controller;

import com.example.portfolioapplication.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
@RestController
@Controller
@RequestMapping("/api/v1/")
public class houseKeepingController {

    @Autowired
    private StockService stockService;
    @PostMapping("/stock")
    String updateStockDetails() throws IOException {
        stockService.downloadAndLoadBhavcopy();
        return "Done";
    }
}
