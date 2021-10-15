package com.projeto.chatbot.util;

import com.projeto.chatbot.data.Filtro;
import com.projeto.chatbot.data.Mensagem;
import com.projeto.chatbot.service.FiltroService;
import com.projeto.chatbot.service.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CargaInicial implements ApplicationRunner {

    @Autowired
    private MensagemService mensagemService;
    @Autowired
    private FiltroService filtroService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (mensagemService.findMensagemById(1).isEmpty()) {
            mensagemService.newMensagem(new Mensagem("palavrão", "Que feio, falado palavrão :(", "1- Sair"));

            filtroService.newFiltro(new Filtro("fdp"));
        }
    }
}
