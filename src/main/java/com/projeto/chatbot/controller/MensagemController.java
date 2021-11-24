package com.projeto.chatbot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.projeto.chatbot.data.Mensagem;
import com.projeto.chatbot.service.MensagemService;
import com.projeto.chatbot.util.RequestEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class MensagemController {


    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Autowired
    private MensagemService mensagemService;

    @GetMapping("/chamada")
    public ResponseEntity<?> teste(@RequestParam(value = "msgCliente") String msgCliente) {

        try {

            URL url = new URL("http://localhost:8080/mensagem?msg_cliente=" + msgCliente);

            // Open a connection(?) on the URL(??) and cast the response(???)
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Now it's "open", we can set the request method, headers etc.
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestProperty(RequestEnum.HEADER_INICIO.getNome(), "s");
            connection.setRequestProperty(RequestEnum.HEADER_NOME_USUARIO.getNome(), "");
            connection.setRequestProperty(RequestEnum.HEADER_NOME_USUARIO.getNome(), "");

            // This line makes the request
            InputStream responseStream = connection.getInputStream();

            // Manually converting the response body InputStream to APOD using Jackson
            ObjectMapper mapper = new ObjectMapper();
            Mensagem msg = mapper.readValue(responseStream, Mensagem.class);

            return ResponseEntity.status(HttpStatus.OK).body(msg);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(path = "/mensagem", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findMensagem(@RequestParam(value = "msg_cliente") String msgCliente,
                                          @RequestHeader(value = "inicio") String inicio,
                                          @RequestHeader(value = "nome", required = false) String nomeCliente,
                                          @RequestHeader(value = "sequencia", required = false) String sequencia) {
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
