package com.atvaula10.atvaula10.exception;

public class NegativeAmountNotAllowedException extends RuntimeException {
    public NegativeAmountNotAllowedException(String message) {
        super(message);
    }
}