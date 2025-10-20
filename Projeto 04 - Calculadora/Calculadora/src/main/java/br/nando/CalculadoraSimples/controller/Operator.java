package br.nando.CalculadoraSimples.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.nando.CalculadoraSimples.exception.UnsupportedMathOperationException;

// Esta é a classe que faz todos os cálculos
public class Operator {

    // Você já tem este
    public Double soma(Double n1, Double n2) {
        return n1 + n2;
    }
    
    // Você já tem este
    public Double sub(Double n1, Double n2) {
        return n1 - n2;
    }

    // --- AQUI COMEÇAM OS NOVOS MÉTODOS ---

    // 2. Multiplicação
    public Double mult(Double n1, Double n2) {
        return n1 * n2;
    }

    // 3. Divisão
    public Double div(Double n1, Double n2) {
        if (n2 == 0) {
            // Usando a SUA classe de exceção!
            throw new UnsupportedMathOperationException("Não é possível dividir por zero.");
        }
        return n1 / n2;
    }

    // 4. Raiz Quadrada
    public Double raizQuadrada(Double n) {
        if (n < 0) {
            // Usando a SUA classe de exceção!
            throw new UnsupportedMathOperationException("Não é possível calcular a raiz quadrada de um número negativo.");
        }
        return Math.sqrt(n);
    }

    // 5. Raiz Cúbica
    public Double raizCubica(Double n) {
        return Math.cbrt(n);
    }

    // 6. Potência
    public Double potencia(Double base, Double expoente) {
        return Math.pow(base, expoente);
    }

    // 7. Converter para Binário
    // Note que o retorno é String
    public String decimalParaBinario(Integer n) {
        return Integer.toBinaryString(n);
    }

    // 8. Elevar 10 a uma potência
    public Double potenciaDeDez(Double expoente) {
        return Math.pow(10, expoente);
    }

    // 9. Fatorial
    // Note que o retorno é Long (número inteiro longo)
    public Long fatorial(Integer n) {
        if (n < 0) {
            // Usando a SUA classe de exceção!
            throw new UnsupportedMathOperationException("Fatorial não é definido para números negativos.");
        }
        if (n == 0 || n == 1) {
            return 1L; // Retorna 1 como Long
        }
        long resultado = 1; // Usa 'long' para caber números grandes
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    // 10. Valor de Pi
    public Double getPi() {
        return Math.PI;
    }
}