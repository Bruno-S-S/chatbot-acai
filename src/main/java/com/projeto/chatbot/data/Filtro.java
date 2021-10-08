package com.projeto.chatbot.data;

import javax.persistence.*;

@Entity
@Table(name = "filtro")
public class Filtro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "palavra")
    private String palavra;

    public Filtro() {
    }

    public Filtro(String palavra) {
        this.palavra = palavra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }
}
