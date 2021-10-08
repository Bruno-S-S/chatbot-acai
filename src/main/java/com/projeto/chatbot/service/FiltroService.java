package com.projeto.chatbot.service;

import com.projeto.chatbot.data.Filtro;

import java.util.List;
import java.util.Optional;

public interface FiltroService {

    public void newFiltro(Filtro filtro);

    public Optional<Filtro> findFiltroById(int id);

    public Filtro findFiltroByPalavra(String palavra);

    public List<Filtro> findFiltros();

    public void deleteFiltro(int id);

}
