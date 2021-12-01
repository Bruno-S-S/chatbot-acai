package com.projeto.chatbot.service.impl;

import com.projeto.chatbot.data.Filtro;
import com.projeto.chatbot.data.Mensagem;
import com.projeto.chatbot.repository.MensagemRepository;
import com.projeto.chatbot.service.MensagemService;
import com.projeto.chatbot.util.MensagensEnum;
import com.projeto.chatbot.util.RequestEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensagemServiceImpl implements MensagemService {

    @Autowired
    private MensagemRepository mensagemRepository;
    @Autowired
    private FiltroServiceImpl filtroService;

    @Override
    public void newMensagem(Mensagem mensagem) {
        mensagemRepository.save(mensagem);
    }

    @Override
    public Mensagem findMensagemById(int id) {
        return mensagemRepository.findById(id).get();
    }

    @Override
    public Mensagem findMensagemByText(String msgCliente) {

        if (filtrarPalavra(msgCliente)) {
            return mensagemRepository.findById(MensagensEnum.PALAVRAO.getId()).get();
        }
        return mensagemRepository.findMensagemByText(msgCliente).get();
    }

    @Override
    public List<Mensagem> findMensagens() {
        return mensagemRepository.findAll();
    }

    @Override
    public void updateMensagem(String msgCliente, String texto, String opcoes, int id) {
        mensagemRepository.updateMensagem(msgCliente, texto, opcoes, id);
    }

    @Override
    public void deleteMensagemById(int id) {
        mensagemRepository.deleteById(id);
    }

    private boolean filtrarPalavra(String mensagem) {
        List<Filtro> palavras = filtroService.findFiltros();

        for (Filtro palavra : palavras) {
            if (mensagem.contains(palavra.getPalavra())) {
                return true;
            }
        }
        return false;
    }

    private Mensagem menuInicial(String nome) {

        Mensagem menuInicial = findMensagemById(MensagensEnum.MENU_INICIAL.getId());

        String inicio = menuInicial.getTexto().substring(0, 12);
        String fim = menuInicial.getTexto().substring(12);

        menuInicial.setTexto(inicio + " " + nome + fim);

        return menuInicial;
    }

    @Override
    public ResponseEntity<?> respostaMsg(String msgCliente, String inicio, String nomeUsuario, String sequencia) {

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(RequestEnum.HEADER_INICIO.getNome(), "s");
        httpHeaders.add(RequestEnum.HEADER_NOME_USUARIO.getNome(), nomeUsuario);
        httpHeaders.add(RequestEnum.HEADER_SEQUENCIA.getNome(), sequencia);

        if (!inicio.equalsIgnoreCase("n")) {
            httpHeaders.set(RequestEnum.HEADER_INICIO.getNome(), "n");
            return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(findMensagemById(MensagensEnum.BEM_VINDO.getId()));
        }

        httpHeaders.set(RequestEnum.HEADER_INICIO.getNome(), "n");

        if (null == nomeUsuario || nomeUsuario.isEmpty()) {
            if (msgCliente.isEmpty()) {
                httpHeaders.set(RequestEnum.HEADER_NOME_USUARIO.getNome(), nomeUsuario);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(httpHeaders).body(findMensagemById(MensagensEnum.SEM_NOME.getId()));
            }
            nomeUsuario = msgCliente;
            httpHeaders.set(RequestEnum.HEADER_NOME_USUARIO.getNome(), nomeUsuario);
            return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(menuInicial(nomeUsuario));
        }

        httpHeaders.set(RequestEnum.HEADER_NOME_USUARIO.getNome(), nomeUsuario);

        if (!msgCliente.isEmpty()) {
            if (msgCliente.equalsIgnoreCase(MensagensEnum.TCHAU.getFrase())) {
                return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(findMensagemById(MensagensEnum.TCHAU.getId()));
            }
            try {
                String pesquisa = (null != sequencia && !sequencia.isEmpty()) ? gerarPesquisa(sequencia, msgCliente) : msgCliente;

                pesquisa = tratarSequencia(msgCliente, pesquisa);

                Mensagem mensagem = findMensagemByText(pesquisa);

                if (null != mensagem) {
                    httpHeaders.set(RequestEnum.HEADER_SEQUENCIA.getNome(), pesquisa);
                }

                return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(mensagem);
            } catch (Exception ex) {
                httpHeaders.set(RequestEnum.HEADER_SEQUENCIA.getNome(), sequencia);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(findMensagemById(MensagensEnum.NAO_ENCONTRADO.getId()));
            }

        } else {
            httpHeaders.set(RequestEnum.HEADER_SEQUENCIA.getNome(), sequencia);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(httpHeaders).body(null);
        }
    }

    private String gerarPesquisa(String sequencia, String msgCliente) {
        return sequencia + "." + msgCliente;
    }

    private String tratarSequencia(String msgCliente, String sequencia) {

        if (msgCliente.contains("0") || msgCliente.contains("*")) {

            return sequencia.substring(0, sequencia.length() - 4);
        }

        return sequencia;
    }
}
