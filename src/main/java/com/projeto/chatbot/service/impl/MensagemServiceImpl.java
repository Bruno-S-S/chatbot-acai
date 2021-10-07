package com.projeto.chatbot.service.impl;

import com.google.gson.Gson;
import com.projeto.chatbot.data.Mensagem;
import com.projeto.chatbot.repository.MensagemRepository;
import com.projeto.chatbot.service.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MensagemServiceImpl implements MensagemService {

    @Autowired
    private MensagemRepository mensagemRepository;

    private Gson gson = new Gson();
    private Mensagem msg = new Mensagem();


    @Override
    public void newMensagem(Mensagem mensagem) {
        mensagemRepository.save(mensagem);
    }

    @Override
    public Optional<Mensagem> findMensagemById(int id) {
        return mensagemRepository.findById(id);
    }

    @Override
    public List<Mensagem> findMensagemByText(String texto) {
        return mensagemRepository.findMensagemByText(texto);
    }

    @Override
    public List<Mensagem> findMensagens() {
        return mensagemRepository.findAll();
    }

    @Override
    public void deleteMensagemById(int id) {
        mensagemRepository.deleteById(id);
    }
}
