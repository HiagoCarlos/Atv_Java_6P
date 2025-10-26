package com.atvaula10_2.atvaula10_2.controller;

import com.atvaula10_2.atvaula10_2.dto.ImcResponseDTO;
import com.atvaula10_2.atvaula10_2.service.ImcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imc") // Define a rota base para este controller
public class ImcController {

    @Autowired
    private ImcService imcService;

    /**
     * Endpoint principal que calcula o IMC e retorna o DTO completo.
     * Acessível via: GET /imc?peso=70&altura=1.75
     */
    @GetMapping
    public ResponseEntity<ImcResponseDTO> getImc(
            @RequestParam("peso") double peso,
            @RequestParam("altura") double altura) {
        
        ImcResponseDTO response = imcService.calcularImcCompleto(peso, altura);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint que retorna apenas a string de classificação.
     * Acessível via: GET /imc/classificacao?peso=70&altura=1.75
     */
    @GetMapping("/classificacao")
    public ResponseEntity<String> getClassificacao(
            @RequestParam("peso") double peso,
            @RequestParam("altura") double altura) {
        
        // Reutilizamos o mesmo método de serviço
        ImcResponseDTO response = imcService.calcularImcCompleto(peso, altura);
        return ResponseEntity.ok(response.classificacao());
    }
}