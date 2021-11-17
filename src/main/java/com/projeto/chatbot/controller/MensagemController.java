package com.projeto.chatbot.controller;

import com.projeto.chatbot.data.Mensagem;
import com.projeto.chatbot.service.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MensagemController {

    @Autowired
    private MensagemService mensagemService;

    @GetMapping("/mensagem")
    public ResponseEntity<?> findMensagem(@RequestParam(value = "msg_cliente") String msgCliente,
                                          HttpServletRequest request) {

        try {
            return mensagemService.respostaMsg(msgCliente, request);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/mensagens")
    public ResponseEntity<?> findMensagens() {
        return ResponseEntity.status(HttpStatus.OK).body(mensagemService.findMensagens());
    }

    @PostMapping("/mensagem")
    public ResponseEntity<?> newMensagem(@RequestBody Mensagem mensagem) {

        try {
            mensagem.setMsgCliente(mensagem.getMsgCliente().toLowerCase());
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
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
