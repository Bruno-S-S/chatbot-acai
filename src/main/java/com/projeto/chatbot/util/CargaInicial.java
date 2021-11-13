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
            mensagemService.newMensagem(new Mensagem(MensagensEnum.BEM_VINDO.getFrase(), "Seja bem vindo! Por favor digite seu nome para começarmos", ""));
            mensagemService.newMensagem(new Mensagem(MensagensEnum.MENU_INICIAL.getFrase(), "Bem vindo, por favor selecione uma das opções:", "1- Fazer pedido,2- Ajuda,3- Sair"));
            mensagemService.newMensagem(new Mensagem(MensagensEnum.SEM_NOME.getFrase(), "Por favor, digite seu nome para continuar", ""));
            mensagemService.newMensagem(new Mensagem(MensagensEnum.TCHAU.getFrase(), "Adeus, até a próxima", ""));
            mensagemService.newMensagem(new Mensagem(MensagensEnum.PALAVRAO.getFrase(), "Que feio, falado palavrão :(", "1- Sair"));

            filtroService.newFiltro(new Filtro(FiltroEnum.PALAVRAO.getFrase()));
        }
    }
}
