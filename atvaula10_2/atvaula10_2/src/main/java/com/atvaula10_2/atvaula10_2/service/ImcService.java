package com.atvaula10_2.atvaula10_2.service;

import com.atvaula10_2.atvaula10_2.dto.ImcResponseDTO;
import com.atvaula10_2.atvaula10_2.exception.DivisionByZeroLikeException;
import com.atvaula10_2.atvaula10_2.exception.InvalidHeightException;
import com.atvaula10_2.atvaula10_2.exception.InvalidWeightException;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class ImcService {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    // Método principal que o controller chamará
    public ImcResponseDTO calcularImcCompleto(double peso, double altura) {
        // 1. Validação
        validarEntradas(peso, altura);

        // 2. Cálculo
        double imc = peso / (altura * altura);

        // 3. Classificação
        String classificacao = classificarImc(imc);

        // 4. Faixa OMS
        String faixaOms = obterFaixaOms(classificacao);

        // 5. Formatar o IMC para 2 casas decimais
        double imcFormatado = Double.parseDouble(df.format(imc).replace(",", "."));


        // 6. Criar e retornar o DTO
        return new ImcResponseDTO(imcFormatado, classificacao, faixaOms);
    }

    // Método de validação privado
    private void validarEntradas(double peso, double altura) {
        // Validação da Altura
        if (altura == 0) {
            throw new DivisionByZeroLikeException("Altura não pode ser zero.");
        }
        if (altura < 0.5 || altura > 2.5) {
            throw new InvalidHeightException("Altura inválida. Deve estar entre 0.5m e 2.5m.");
        }

        // Validação do Peso
        if (peso < 2 || peso > 400) {
            throw new InvalidWeightException("Peso inválido. Deve estar entre 2kg e 400kg.");
        }
    }

    // Método de classificação privado
    private String classificarImc(double imc) {
        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc < 24.9) {
            return "Peso normal";
        } else if (imc < 29.9) {
            return "Sobrepeso";
        } else if (imc < 34.9) {
            return "Obesidade Grau I";
        } else if (imc < 39.9) {
            return "Obesidade Grau II";
        } else {
            return "Obesidade Grau III (Mórbida)";
        }
    }

    // Método privado para faixa OMS
    private String obterFaixaOms(String classificacao) {
        if ("Peso normal".equals(classificacao)) {
            return "Faixa de peso normal recomendada pela OMS: 18.5 a 24.9";
        }
        // Para outras classificações, apenas retornamos a própria classificação
        // ou uma mensagem genérica.
        return "Consulte um profissional de saúde.";
    }
}