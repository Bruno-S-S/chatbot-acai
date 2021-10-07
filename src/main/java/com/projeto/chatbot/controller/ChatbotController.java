package com.projeto.chatbot.controller;

import com.projeto.chatbot.data.Mensagem;
import com.projeto.chatbot.service.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatbotController {

    @Autowired
    private MensagemService mensagemService;

    @GetMapping("/mensagem")
    public ResponseEntity<?> findMensagem(@RequestParam(value = "id_mensagem", required = false) Integer id,
                                          @RequestParam(value = "msgCliente", required = false) String mensagem) {

        if (id != null && mensagem == null) {
            return ResponseEntity.status(HttpStatus.OK).body(mensagemService.findMensagemById(id));
        } else if (id == null && mensagem != null) {
            return ResponseEntity.status(HttpStatus.OK).body(mensagemService.findMensagemByText(mensagem));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/mensagens")
    public ResponseEntity<?> findMensagens() {
        return ResponseEntity.status(HttpStatus.OK).body(mensagemService.findMensagens());
    }

    @PostMapping("/mensagem")
    public ResponseEntity<?> newMensagem(@RequestBody Mensagem mensagem) {

        try {
            mensagemService.newMensagem(mensagem);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/mensagem")
    public ResponseEntity<?> deleteMensagem(@RequestParam(value = "id_mensagem") int id) {

        try {
            mensagemService.deleteMensagemById(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
