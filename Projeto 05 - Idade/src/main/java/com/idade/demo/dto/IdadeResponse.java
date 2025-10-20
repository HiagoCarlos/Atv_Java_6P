package com.idade.demo.dto; 
public class IdadeResponse {

    private String nomeCompleto;
    private String idadeCalculada;
    
    // Construtor
    public IdadeResponse(String nomeCompleto, String idadeCalculada) {
        this.nomeCompleto = nomeCompleto;
        this.idadeCalculada = idadeCalculada;
    }

    // Getters (obrigatório para o Spring converter em JSON)
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getIdadeCalculada() {
        return idadeCalculada;
    }
}