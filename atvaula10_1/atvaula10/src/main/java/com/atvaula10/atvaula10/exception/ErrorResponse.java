package com.atvaula10.atvaula10.exception;

public record ErrorResponse(int status, String error, String message) {
}