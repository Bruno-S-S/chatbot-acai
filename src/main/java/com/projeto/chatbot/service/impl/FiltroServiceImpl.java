package com.projeto.chatbot.service.impl;

import com.projeto.chatbot.data.Filtro;
import com.projeto.chatbot.repository.FiltroRepository;
import com.projeto.chatbot.service.FiltroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FiltroServiceImpl implements FiltroService {

    @Autowired
    private FiltroRepository filtroRepository;

    @Override
    public void newFiltro(Filtro filtro) {
        filtroRepository.save(filtro);
    }

    @Override
    public Optional<Filtro> findFiltroById(int id) {
        return filtroRepository.findById(id);
    }

    @Override
    public Filtro findFiltroByPalavra(String palavra) {

        return filtroRepository.findFiltroByPalavra(palavra);
    }

    @Override
    public List<Filtro> findFiltros() {
        return filtroRepository.findAll();
    }

    @Override
    public void updateFiltro(String palavra, int id) {
        filtroRepository.updateFiltro(palavra, id);
    }

    @Override
    public void deleteFiltroById(int id) {
        filtroRepository.deleteById(id);
    }
}
