package com.atvaula10_2.atvaula10_2.dto;

public record ImcResponseDTO(
    double imc,
    String classificacao,
    String faixaRecomendadaOms
) {
}