package com.projeto.chatbot.service;

import com.projeto.chatbot.data.Mensagem;
import com.projeto.chatbot.data.MensagemResponse;

import java.util.List;
import java.util.Optional;

public interface MensagemService {

    public void newMensagem(Mensagem mensagem);

    public Optional<Mensagem> findMensagemById(int id);

    public Optional<MensagemResponse> findMensagemByText(String msgCliente);

    public List<Mensagem> findMensagens();

    public void deleteMensagemById(int id);
}
