package com.projeto.chatbot.util;

public enum FiltroEnum {

    PALAVRAO(1, "fdp");

    private String frase;
    private int id;

    FiltroEnum(int id, String frase) {
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
