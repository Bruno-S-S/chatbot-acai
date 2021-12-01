package com.projeto.chatbot.repository;

import com.projeto.chatbot.data.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {

    @Query(value = "select * from mensagem where msgcliente = :msgcliente", nativeQuery = true)
    Optional<Mensagem> findMensagemByText(@Param("msgcliente") String msgCliente);

    @Transactional
    @Modifying
    @Query(value = "update mensagem set msgcliente = :msgcliente, texto = :texto, opcoes = :opcoes where id = :id", nativeQuery = true)
    void updateMensagem(@Param("msgcliente") String msgCliente,
                        @Param("texto") String texto,
                        @Param("opcoes") String opcoes,
                        @Param("id") int id);


}
