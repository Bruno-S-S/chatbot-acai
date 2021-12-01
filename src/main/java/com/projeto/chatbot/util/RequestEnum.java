package com.projeto.chatbot.util;

public enum RequestEnum {

    HEADER_INICIO("inicio"),
    HEADER_NOME_USUARIO("nome"),
    HEADER_SEQUENCIA("sequencia");

    private String nome;

    RequestEnum(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
