package com.projeto.chatbot.controller;

import com.projeto.chatbot.data.Mensagem;
import com.projeto.chatbot.service.MensagemService;
import com.projeto.chatbot.util.MensagensEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class MensagemController {

    @Autowired
    private MensagemService mensagemService;

    @GetMapping("/mensagem")
    public ResponseEntity<?> findMensagem(@RequestParam(value = "id_mensagem", required = false) Integer id,
                                          @RequestParam(value = "msg_cliente", required = false) String msgCliente,
                                          HttpServletRequest request) {

        String nome_usuario = (String) request.getSession().getAttribute("NOME");
        Boolean inicio = (Boolean) request.getSession().getAttribute("INICIO");

        if (nome_usuario == null && inicio == null) {
            request.getSession().setAttribute("INICIO", false);
            return ResponseEntity.status(HttpStatus.OK).body(mensagemService.findMensagemById(MensagensEnum.BEM_VINDO.getId()));
        }

        if (nome_usuario == null && !inicio) {
            if (msgCliente == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemService.findMensagemById(MensagensEnum.SEM_NOME.getId())  );
            }
            request.getSession().setAttribute("NOME", msgCliente);
            return ResponseEntity.status(HttpStatus.OK).body(mensagemService.menuInicial(request.getSession().getAttribute("NOME").toString()));
        }

        if (id != null && msgCliente == null) {
            if (id == MensagensEnum.TCHAU.getId()) {
                request.getSession().invalidate();
            }
            return ResponseEntity.status(HttpStatus.OK).body(mensagemService.findMensagemById(id));
        } else if (id == null && msgCliente != null) {
            if (msgCliente.equalsIgnoreCase(MensagensEnum.TCHAU.getFrase())) {
                request.getSession().invalidate();
            }
            return ResponseEntity.status(HttpStatus.OK).body(mensagemService.findMensagemByText(msgCliente.toLowerCase()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/mensagens")
    public ResponseEntity<?> findMensagens(HttpSession session) {
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
