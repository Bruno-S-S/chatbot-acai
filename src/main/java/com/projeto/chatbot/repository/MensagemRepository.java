package com.projeto.chatbot.repository;

import com.projeto.chatbot.data.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {

    @Query(value = "select * from mensagem where id = :id", nativeQuery = true)
    public Mensagem findMensagemById (@Param("id") int id);

    @Query(value = "delete from mensagem where id = :id", nativeQuery = true)
    public Mensagem deleteMensagemById (@Param("id") int id);
}
