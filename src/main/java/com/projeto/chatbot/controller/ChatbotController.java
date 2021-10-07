package com.projeto.chatbot.controller;

import com.projeto.chatbot.service.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatbotController {

    @Autowired
    private MensagemService mensagemService;

    @GetMapping("/mensagem")
    //public ResponseEntity<?> mensagem(@RequestHeader(value = "mensagem") String msgUsuario) {
    public ResponseEntity<?> mensagem(@RequestParam(value = "mensagem") String msgUsuario) {

        return ResponseEntity.status(HttpStatus.OK).body(mensagemService.mensagem(msgUsuario));
    }
}
