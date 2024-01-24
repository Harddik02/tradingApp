package com.example.portfolioapplication.controller;

import com.example.portfolioapplication.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@RestController
@Controller
@RequestMapping("/api/v1/")
public class houseKeepingController {

    @Autowired
    private StockService stockService;
    @PostMapping(value="/stock/update",consumes={"Multipart/form-data"})
    String updateStockDetails(@RequestPart("bhavfile") MultipartFile bhavfile) throws IOException {

        stockService.downloadAndLoadBhavcopy(bhavfile);
        return "Done";
    }
}
