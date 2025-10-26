package com.atvaula10.atvaula10.validation;

import com.atvaula10.atvaula10.exception.NegativeAmountNotAllowedException;
import com.atvaula10.atvaula10.exception.UnsupportedUnitException;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ConversionValidator {

    private static final Set<String> SUPPORTED_CURRENCIES = Set.of("USD", "BRL", "EUR");
    private static final Set<String> SUPPORTED_TEMPERATURES = Set.of("C", "F");
    private static final Set<String> SUPPORTED_DISTANCES = Set.of("KM", "MI");
    private static final double MAX_ALLOWED_VALUE = 1_000_000_000.0;

    private void validateValue(double value) {
        if (value < 0) {
            throw new NegativeAmountNotAllowedException("Valor não pode ser negativo: " + value);
        }
        if (value > MAX_ALLOWED_VALUE) {
            throw new IllegalArgumentException("Valor excede o limite máximo permitido de " + MAX_ALLOWED_VALUE);
        }
    }

    private void validateUnits(String from, String to, Set<String> supportedUnits) {
        if (!supportedUnits.contains(from.toUpperCase())) {
            throw new UnsupportedUnitException("Unidade 'de' (from) não suportada: " + from);
        }
        if (!supportedUnits.contains(to.toUpperCase())) {
            throw new UnsupportedUnitException("Unidade 'para' (to) não suportada: " + to);
        }
        if (from.equalsIgnoreCase(to)) {
            throw new UnsupportedUnitException("As unidades 'de' (from) e 'para' (to) não podem ser iguais.");
        }
    }

    public void validateCurrency(String from, String to, double amount) {
        validateValue(amount);
        validateUnits(from, to, SUPPORTED_CURRENCIES);
    }

    public void validateTemperature(String from, String to, double value) {
        if (value < -273.15) { 
            throw new NegativeAmountNotAllowedException("Temperatura abaixo do zero absoluto não é permitida.");
        }
        if (value > 2000) {
             throw new IllegalArgumentException("Valor de temperatura excede o limite máximo permitido de 2000");
        }
        validateUnits(from, to, SUPPORTED_TEMPERATURES);
    }
    
    public void validateDistance(String from, String to, double value) {
        validateValue(value);
        validateUnits(from, to, SUPPORTED_DISTANCES);
    }
}