package com.projeto.chatbot.service;

import com.projeto.chatbot.data.Mensagem;

import java.util.List;
import java.util.Optional;

public interface MensagemService {

    public void newMensagem(Mensagem mensagem);

    public Optional<Mensagem> findMensagemById(int id);

    public List<Mensagem> findMensagemByText(String texto);

    public List<Mensagem> findMensagens();

    public void deleteMensagemById(int id);
}
