package com.projeto.chatbot.service;

import com.projeto.chatbot.data.Mensagem;

public interface MensagemService {

    public void newMensagem(Mensagem mensagem);

    public Mensagem findMensagem(int id);

    public void deleteMensagemById(int id);
}
