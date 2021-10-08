package com.projeto.chatbot.data.impl;

import com.projeto.chatbot.data.MensagemResponse;

import java.util.Arrays;
import java.util.List;

public class MensagemResponseImpl implements MensagemResponse {

    private String texto;
    private String opcoes;

    @Override
    public String getTexto() {
        return null;
    }

    @Override
    public List<String> getOpcoes() {
        return Arrays.asList(opcoes.split(","));
    }


}
