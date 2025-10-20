package br.nando.CalculadoraSimples.service; // Pacote correto!

import org.springframework.stereotype.Service;
import br.nando.CalculadoraSimples.exception.UnsupportedMathOperationException; // Usando SUA exceção!

@Service
public class CalculadoraService {

    // 1. Subtração
    public double subtrair(double n1, double n2) {
        return n1 - n2;
    }

    // 2. Multiplicação
    public double multiplicar(double n1, double n2) {
        return n1 * n2;
    }

    // 3. Divisão
    public double dividir(double n1, double n2) {
        if (n2 == 0) {
            // Vamos usar a sua classe de exceção personalizada!
            throw new UnsupportedMathOperationException("Não é possível dividir por zero.");
        }
        return n1 / n2;
    }

    // 4. Raiz Quadrada
    public double raizQuadrada(double n) {
        if (n < 0) {
            // Vamos usar a sua classe de exceção personalizada!
            throw new UnsupportedMathOperationException("Não é possível calcular a raiz quadrada de um número negativo.");
        }
        return Math.sqrt(n);
    }

    // 5. Raiz Cúbica
    public double raizCubica(double n) {
        return Math.cbrt(n);
    }

    // 6. Potência
    public double potencia(double base, double expoente) {
        return Math.pow(base, expoente);
    }

    // 7. Converter para Binário
    public String decimalParaBinario(int n) {
        return Integer.toBinaryString(n);
    }

    // 8. Elevar 10 a uma potência
    public double potenciaDeDez(double expoente) {
        return Math.pow(10, expoente);
    }

    // 9. Fatorial
    public long fatorial(int n) {
        if (n < 0) {
            // Vamos usar a sua classe de exceção personalizada!
            throw new UnsupportedMathOperationException("Fatorial não é definido para números negativos.");
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        long resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    // 10. Valor de Pi
    public double getPi() {
        return Math.PI;
    }
}