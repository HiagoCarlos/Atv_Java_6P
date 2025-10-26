package com.atvaula10.atvaula10.controller;

import com.atvaula10.atvaula10.dto.ConversionResponse;
import com.atvaula10.atvaula10.service.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/convert")
public class ConversionController {

    @Autowired
    private ConversionService conversionService;

    @GetMapping("/currency")
    public ResponseEntity<ConversionResponse> convertCurrency(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam double amount) {
        
        ConversionResponse response = conversionService.convertCurrency(from, to, amount);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/unit/temperature")
    public ResponseEntity<ConversionResponse> convertTemperature(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam double value) {
        
        ConversionResponse response = conversionService.convertTemperature(from, to, value);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/unit/distance")
    public ResponseEntity<ConversionResponse> convertDistance(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam double value) {

        ConversionResponse response = conversionService.convertDistance(from, to, value);
        return ResponseEntity.ok(response);
    }
}