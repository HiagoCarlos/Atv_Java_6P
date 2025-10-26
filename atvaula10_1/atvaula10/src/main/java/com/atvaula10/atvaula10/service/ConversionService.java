package com.atvaula10.atvaula10.service;

import com.atvaula10.atvaula10.dto.ConversionResponse;
import com.atvaula10.atvaula10.validation.ConversionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ConversionService {

    @Autowired
    private ConversionValidator validator;

    private static final Map<String, Double> CURRENCY_RATES_TO_USD = Map.of(
            "USD", 1.0,
            "BRL", 1 / 5.40,
            "EUR", 1.08
    );

    private static final double KM_TO_MILE = 0.621371;

    public ConversionResponse convertCurrency(String from, String to, double amount) {
        validator.validateCurrency(from, to, amount);

        String fromUpper = from.toUpperCase();
        String toUpper = to.toUpperCase();

        double amountInUsd = amount * CURRENCY_RATES_TO_USD.get(fromUpper);
        double convertedAmount = amountInUsd / CURRENCY_RATES_TO_USD.get(toUpper);

        String resultText = String.format("%.2f %s = %.2f %s", amount, fromUpper, convertedAmount, toUpper);
        
        return new ConversionResponse(fromUpper, toUpper, amount, convertedAmount, resultText);
    }

    public ConversionResponse convertTemperature(String from, String to, double value) {
        validator.validateTemperature(from, to, value);
        
        String fromUpper = from.toUpperCase();
        String toUpper = to.toUpperCase();
        double convertedValue;

        if (fromUpper.equals("C") && toUpper.equals("F")) {
            convertedValue = (value * 9.0 / 5.0) + 32.0;
        } else {
            convertedValue = (value - 32.0) * 5.0 / 9.0;
        }
        
        String resultText = String.format("%.1f°%s = %.1f°%s", value, fromUpper, convertedValue, toUpper);

        return new ConversionResponse(fromUpper, toUpper, value, convertedValue, resultText);
    }
    
    public ConversionResponse convertDistance(String from, String to, double value) {
        validator.validateDistance(from, to, value);

        String fromUpper = from.toUpperCase();
        String toUpper = to.toUpperCase();
        double convertedValue;

        if (fromUpper.equals("KM") && toUpper.equals("MI")) {
            convertedValue = value * KM_TO_MILE;
        } else {
            convertedValue = value / KM_TO_MILE;
        }

        String resultText = String.format("%.2f %s = %.2f %s", value, fromUpper, convertedValue, toUpper);

        return new ConversionResponse(fromUpper, toUpper, value, convertedValue, resultText);
    }
}