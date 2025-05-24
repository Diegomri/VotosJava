package com.votos.v2.controller;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.votos.v2.service.DataBaseService;
import com.votos.v2.service.ReadCSVService;

@RequestMapping("/api")
@RestController
public class ApiController {

    private final ReadCSVService csvService;
    private final DataBaseService dataBaseService;

    public ApiController(ReadCSVService csvService, DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
        this.csvService = csvService;
    }

    @GetMapping("/getCsv")
    public List<Map<String, String>> getCsvContent() {
        return csvService.readCsvContent();
    }   

    @GetMapping("/saveData")
    public String saveDataToDatabase() {
        dataBaseService.saveDataToDatabase();
        return "Data saved to database";
    }

    @GetMapping("/getApi")
    public List<Map<String, String>> getData() {
        return csvService.readCsvContent();
    }
}