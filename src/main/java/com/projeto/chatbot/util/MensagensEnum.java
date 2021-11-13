package com.projeto.chatbot.util;

public enum MensagensEnum {

    BEM_VINDO(1, "bem vindo"),
    MENU_INICIAL(2, "menu inicial"),
    SEM_NOME(3, "digite seu nome"),
    TCHAU(4,"tchau"),
    PALAVRAO(5, "palavrao");

    private String frase;
    private int id;

    MensagensEnum(int id, String frase) {
        this.id = id;
        this.frase = frase;
    }

    public int getId() {
        return id;
    }

    public String getFrase() {
        return frase;
    }
}
