package com.projeto.chatbot.repository;

import com.projeto.chatbot.data.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {

    @Query(value = "select * from mensagem where texto like %:texto%", nativeQuery = true)
    public List<Mensagem> findMensagemByText(@Param("texto") String texto);

}
