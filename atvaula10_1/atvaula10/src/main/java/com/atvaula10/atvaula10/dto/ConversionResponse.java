package com.atvaula10.atvaula10.dto;

public record ConversionResponse(
        String fromUnit,
        String toUnit,
        double originalValue,
        double convertedValue,
        String result
) {
}