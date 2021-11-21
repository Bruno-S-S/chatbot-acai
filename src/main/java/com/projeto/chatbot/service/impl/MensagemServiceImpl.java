package com.projeto.chatbot.service.impl;

import com.projeto.chatbot.data.Filtro;
import com.projeto.chatbot.data.Mensagem;
import com.projeto.chatbot.repository.MensagemRepository;
import com.projeto.chatbot.service.MensagemService;
import com.projeto.chatbot.util.MensagensEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

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
    public Optional<Mensagem> findMensagemById(int id) {
        return mensagemRepository.findById(id);
    }

    @Override
    public Optional<Mensagem> findMensagemByText(String msgCliente) {

        if (filtrarPalavra(msgCliente)) {
            return mensagemRepository.findById(MensagensEnum.PALAVRAO.getId());
        }
        return mensagemRepository.findMensagemByText(msgCliente);
    }

    @Override
    public List<Mensagem> findMensagens() {
        return mensagemRepository.findAll();
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

        Optional<Mensagem> menuInicial = findMensagemById(MensagensEnum.MENU_INICIAL.getId());

        if(menuInicial.isPresent()) {
            String inicio = menuInicial.get().getTexto().substring(0, 12);

            String fim = menuInicial.get().getTexto().substring(12);

            menuInicial.get().setTexto(inicio + " " + nome + fim);

            return menuInicial.get();
        }

        return new Mensagem();
    }

    @Override
    public ResponseEntity<?> respostaMsg(String msgCliente, HttpServletRequest request) {

        String nomeUsuario = (String) request.getSession().getAttribute("NOME");
        Boolean inicio = (Boolean) request.getSession().getAttribute("INICIO");

        if (nomeUsuario == null && inicio == null) {
            request.getSession().setAttribute("INICIO", false);
            return ResponseEntity.status(HttpStatus.OK).body(findMensagemById(MensagensEnum.BEM_VINDO.getId()));
        }

        if (nomeUsuario == null && !inicio) {
            if (msgCliente == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(findMensagemById(MensagensEnum.SEM_NOME.getId()));
            }
            request.getSession().setAttribute("NOME", msgCliente);
            return ResponseEntity.status(HttpStatus.OK).body(menuInicial(request.getSession().getAttribute("NOME").toString()));
        }

        if (msgCliente != null) {
            if (msgCliente.equalsIgnoreCase(MensagensEnum.TCHAU.getFrase())) {
                request.getSession().invalidate();
            }
            Optional<Mensagem> resposta = findMensagemByText(msgCliente.toLowerCase());

            if(resposta.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(resposta);
            } else {
                String sequencia = request.getAttribute("sequencia").toString();
                request.setAttribute("sequencia", (sequencia.isEmpty()) ? msgCliente : sequencia + msgCliente);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(findMensagemById(MensagensEnum.NAO_ENCONTRADO.getId()));
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
