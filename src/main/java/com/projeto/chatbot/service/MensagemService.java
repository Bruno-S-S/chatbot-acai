package com.projeto.chatbot.service;

import com.projeto.chatbot.data.Mensagem;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MensagemService {

    public void newMensagem(Mensagem mensagem);

    public Mensagem findMensagemById(int id);

    public Mensagem findMensagemByText(String msgCliente);

    public List<Mensagem> findMensagens();

    public void updateMensagem(String msgCliente, String texto, String opcoes, int id);

    public void deleteMensagemById(int id);

    public ResponseEntity<?> respostaMsg(String msgCliente, String inicio, String nomeCliente, String sequencia);
}
