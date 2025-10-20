package com.idade.demo.controller; // Pacote correto!

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idade.demo.dto.IdadeResponse; // Importando o DTO
import com.idade.demo.service.IdadeService; // Importando o Service

@RestController // <-- Avisa que é um Controller de API REST
@RequestMapping("/idade") // Todas as rotas aqui dentro vão começar com /idade
public class IdadeController {

    // O Spring vai "conectar" o IdadeService aqui automaticamente
    @Autowired
    private IdadeService idadeService;

    @GetMapping("/calcular") // A rota final será /idade/calcular
    public IdadeResponse calcularIdade(
            
            @RequestParam(value = "nome") String nome, 
            @RequestParam(value = "sobrenome", required = false) String sobrenome, 
            
            // O Spring vai converter o texto "yyyy-MM-dd" para LocalDate
            @RequestParam(value = "dataNascimento") 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataNascimento
            
    ) {
        // Manda o Service fazer o trabalho
        return idadeService.calcularIdade(nome, sobrenome, dataNascimento);
    }
    
    // --- Tratamento de Erro Básico ---
    // Como este projeto é novo, ele não tem seu "CustomizedExceptionHandler"
    // Então, adicionamos este método simples para capturar o erro do Service
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        // Retorna o erro 400 (Bad Request) com a mensagem
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}