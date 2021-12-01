package com.projeto.chatbot.repository;

import com.projeto.chatbot.data.Filtro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface FiltroRepository extends JpaRepository<Filtro, Integer> {

    @Query(value = "select * from filtro where palavra = :palavra", nativeQuery = true)
    public Filtro findFiltroByPalavra(@Param("palavra") String palavra);

    @Transactional
    @Modifying
    @Query(value = "update filtro set palavra = :palavra where id = :id", nativeQuery = true)
    void updateFiltro(@Param("palavra") String palavra,
                        @Param("id") int id);
}
