package com.projeto.chatbot.data;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "MENSAGEM")
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "mensagem", nullable = false)
    private String mensagem;

    @Column(name = "opcoes")
    private String opcoes;

    public Mensagem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<String> getOpcoes() {
        return Arrays.asList(opcoes.split(","));
    }

    public void setOpcoes(String opcoes) {
        this.opcoes = opcoes;
    }
}
