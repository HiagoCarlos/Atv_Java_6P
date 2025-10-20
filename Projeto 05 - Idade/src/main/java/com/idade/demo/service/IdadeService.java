package com.idade.demo.service; // Pacote correto!

import java.time.LocalDate;
import java.time.Period;
import org.springframework.stereotype.Service;
import com.idade.demo.dto.IdadeResponse; // Importando o DTO que acabamos de criar

@Service // <-- Avisa ao Spring que esta é uma classe de serviço
public class IdadeService {

    public IdadeResponse calcularIdade(String nome, String sobrenome, LocalDate dataNascimento) {
        
        LocalDate hoje = LocalDate.now();

        // 1. Validação
        if (dataNascimento.isAfter(hoje)) {
            // Joga um erro simples
            throw new IllegalArgumentException("A data de nascimento não pode ser no futuro.");
        }

        // 2. Montar o nome completo
        String nomeCompleto = nome;
        if (sobrenome != null && !sobrenome.isBlank()) {
            nomeCompleto += " " + sobrenome;
        }

        // 3. O "Pulo do Gato": Calcular o Período
        Period periodo = Period.between(dataNascimento, hoje);
        
        int anos = periodo.getYears();
        int meses = periodo.getMonths();
        int dias = periodo.getDays();

        // 4. Formatar a string de resposta
        String idadeCalculada = String.format("%d anos, %d meses e %d dias", anos, meses, dias);

        // 5. Retornar o objeto de resposta
        return new IdadeResponse(nomeCompleto, idadeCalculada);
    }
}