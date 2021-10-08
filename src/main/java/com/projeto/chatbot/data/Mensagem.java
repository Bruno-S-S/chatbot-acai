package com.projeto.chatbot.data;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "mensagem")
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "msgcliente", nullable = false)
    private String msgCliente;

    @Column(name = "texto", nullable = false)
    private String texto;

    @Column(name = "opcoes")
    private String opcoes;

    public Mensagem() {
    }

    public Mensagem(String msgCliente, String texto, String opcoes) {
        this.msgCliente = msgCliente;
        this.texto = texto;
        this.opcoes = opcoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsgCliente() {
        return msgCliente;
    }

    public void setMsgCliente(String msgCliente) {
        this.msgCliente = msgCliente;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public List<String> getOpcoes() {
        return Arrays.asList(opcoes.split(","));
    }

    public void setOpcoes(String opcoes) {
        this.opcoes = opcoes;
    }
}
