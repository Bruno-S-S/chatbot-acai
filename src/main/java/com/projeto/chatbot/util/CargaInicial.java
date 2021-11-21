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
    public void run(ApplicationArguments args) {

        if (mensagemService.findMensagemById(1).isEmpty()) {
            mensagemService.newMensagem(new Mensagem(MensagensEnum.BEM_VINDO.getFrase(), "Seja bem vindo! Por favor digite seu nome para começarmos", ""));
            mensagemService.newMensagem(new Mensagem(MensagensEnum.MENU_INICIAL.getFrase(), "Bem vindo(a), por favor selecione uma das opções:", "1- Fazer pedido,2- Ajuda,3- Sair"));
            mensagemService.newMensagem(new Mensagem(MensagensEnum.SEM_NOME.getFrase(), "Por favor, digite seu nome para continuar", ""));
            mensagemService.newMensagem(new Mensagem(MensagensEnum.TCHAU.getFrase(), "Adeus, até a próxima", ""));
            mensagemService.newMensagem(new Mensagem(MensagensEnum.PALAVRAO.getFrase(), "Que feio, falado palavrão :(", "1- Sair"));
            mensagemService.newMensagem(new Mensagem(MensagensEnum.PIADA_QUEIMADURA.getFrase(), "Porque uma queimadura leve nunca vai fazer faculdade? Porque ela só vai até o 1° grau", ""));
            mensagemService.newMensagem(new Mensagem(MensagensEnum.PIADA_CARRO.getFrase(), "Porque um carro de corrida não pode correr em uma pista pequena? Porque é um curto circuito", ""));
            mensagemService.newMensagem(new Mensagem(MensagensEnum.NAO_ENCONTRADO.getFrase(), "Resposta não encontrada, por favor verifique a opção desejada e tente novamente", ""));
            mensagemService.newMensagem(new Mensagem(MensagensEnum.ERRO_GENERICO.getFrase(), "Erro não tratado, tente de novo", ""));
            mensagemService.newMensagem(new Mensagem(MensagensEnum.FAZER_PEDIDO.getFrase(), "Para realizar um pedido digite apenas o número da opção desejada. A qualquer momento digite \"ajuda\" para ver esta mensagem novamente", ""));

            filtroService.newFiltro(new Filtro(FiltroEnum.PALAVRAO.getFrase()));
        }
    }
}
