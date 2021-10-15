package com.projeto.chatbot.repository;

import com.projeto.chatbot.data.Mensagem;
import com.projeto.chatbot.data.MensagemResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {

//    @Query("select msg from Mensagem msg where msg.msgCliente like %:msgCliente%")
//    Optional<MensagemResponse> findMensagemByText(@Param("msgCliente") String msgCliente);

    @Query(value = "select * from mensagem where msgcliente like %:msgcliente%", nativeQuery = true)
    Optional<Mensagem> findMensagemByText(@Param("msgcliente") String msgCliente);
}
