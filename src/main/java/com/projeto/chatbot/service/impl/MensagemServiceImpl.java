package com.projeto.chatbot.service.impl;

import com.projeto.chatbot.data.Filtro;
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
    @Autowired
    private FiltroServiceImpl filtroService;

    @Override
    public void newMensagem(Mensagem mensagem) {
        mensagemRepository.save(mensagem);
    }

    @Override
    public Optional<Mensagem> findMensagemById(int id) {
        return mensagemRepository.findById(id);
    }

    @Override
    public Optional<Mensagem> findMensagemByText(String msgCliente) {

        if(filtrarPalavra(msgCliente)) {
            return mensagemRepository.findById(1);
        }
        return mensagemRepository.findMensagemByText(msgCliente);
    }

    @Override
    public List<Mensagem> findMensagens() {
        return mensagemRepository.findAll();
    }

    @Override
    public void deleteMensagemById(int id) {
        mensagemRepository.deleteById(id);
    }

    private boolean filtrarPalavra(String mensagem) {
        List<Filtro> palavras = filtroService.findFiltros();

        for(Filtro palavra : palavras) {
            if(mensagem.contains(palavra.getPalavra())) {
                return true;
            }
            break;
        }
        return false;
    }
}
