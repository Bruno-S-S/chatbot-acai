package com.projeto.chatbot.controller;

import com.projeto.chatbot.data.Mensagem;
import com.projeto.chatbot.service.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MensagemController {

    @Autowired
    private MensagemService mensagemService;

    @GetMapping(path = "/mensagem", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findMensagem(@RequestParam(value = "msg_cliente") String msgCliente,
                                          @RequestParam(value = "inicio") String inicio,
                                          @RequestParam(value = "nome", required = false) String nomeCliente,
                                          @RequestParam(value = "sequencia", required = false) String sequencia) {
        try {
            return mensagemService.respostaMsg(msgCliente, inicio, nomeCliente, sequencia);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(path = "/mensagens", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @PatchMapping("/mensagem")
    public ResponseEntity<?> updateMensagem(@RequestParam(value = "msg_cliente") String msgCliente,
                                            @RequestParam(value = "texto") String texto,
                                            @RequestParam(value = "opcoes") String opcoes,
                                            @RequestParam(value = "id") int id) {
        try {
            mensagemService.updateMensagem(msgCliente, texto, opcoes, id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
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
