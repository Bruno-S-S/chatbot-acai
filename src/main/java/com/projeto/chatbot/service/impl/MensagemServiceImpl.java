package com.projeto.chatbot.service.impl;

import com.google.gson.Gson;
import com.projeto.chatbot.data.Mensagem;
import com.projeto.chatbot.service.MensagemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MensagemServiceImpl implements MensagemService {

    private Gson gson = new Gson();
    private Mensagem msg = new Mensagem();
    List<String> opcoes = new ArrayList<>();

    @Override
    public String mensagem(String msgUsuario) {

        msgUsuario = msgUsuario.toLowerCase();

        switch (msgUsuario) {
            case "ola":
                return gson.toJson(msg);
            case "tudo bem?":
                return "Estou muito bem, e você?";
            case "1":
                return pedido();
            default:
                return "Não entendi sua mensagem, por favor tente novamente";
        }
    }

    public List<String> menu() {
        return Arrays.asList("Digite o número da opção:",
                "1- Fazer pedido",
                "2- Ajuda",
                "3- Sair");
    }

    public String pedido() {
        return "\nEscolha o que vai em seu açai:\n" +
                "1- Leite condensado\n" +
                "2- MM's\n" +
                "3- Morango";
    }
    public String sair() {
        return "Muito obrigado pelo contato, até a próxima :)";
    }
}
