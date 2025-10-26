package com.atvaula10_2.atvaula10_2.exception;

// Esta exceção é para o caso específico de altura = 0
public class DivisionByZeroLikeException extends IllegalArgumentException {
    public DivisionByZeroLikeException(String message) {
        super(message);
    }
}