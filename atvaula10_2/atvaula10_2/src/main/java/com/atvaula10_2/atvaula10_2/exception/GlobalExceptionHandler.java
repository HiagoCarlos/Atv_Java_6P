package com.atvaula10_2.atvaula10_2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@ControllerAdvice // Indica que esta classe tratará exceções globalmente
public class GlobalExceptionHandler {

    // Trata as exceções de altura e peso (que são tipos de 'IllegalArgumentException')
    @ExceptionHandler({InvalidHeightException.class, InvalidWeightException.class, DivisionByZeroLikeException.class})
    public ResponseEntity<Object> handleValidationExceptions(IllegalArgumentException ex, WebRequest request) {
        
        // Retorna um JSON simples com a mensagem de erro
        Map<String, String> body = Map.of(
            "status", "400",
            "error", "Bad Request",
            "message", ex.getMessage() // Pega a mensagem definida no service
        );
        
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // Um handler genérico para parâmetros ausentes (ex: /imc?peso=70 sem altura)
    @ExceptionHandler(org.springframework.web.bind.MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingParams(org.springframework.web.bind.MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        Map<String, String> body = Map.of(
            "status", "400",
            "error", "Bad Request",
            "message", "Parâmetro obrigatório ausente: " + name
        );
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}