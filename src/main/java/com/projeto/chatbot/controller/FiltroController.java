package com.projeto.chatbot.controller;

import com.projeto.chatbot.data.Filtro;
import com.projeto.chatbot.service.FiltroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FiltroController {

    @Autowired
    private FiltroService filtroService;

    @GetMapping("/filtro")
    public ResponseEntity<?> findFiltro(@RequestParam(value = "id", required = false) Integer id,
                                        @RequestParam(value = "palavra", required = false) String palavra) {

        if (id != null && palavra == null) {
            return ResponseEntity.status(HttpStatus.OK).body(filtroService.findFiltroById(id));
        } else if (id == null && palavra != null) {
            return ResponseEntity.status(HttpStatus.OK).body(filtroService.findFiltroByPalavra(palavra.toLowerCase()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/filtros")
    public ResponseEntity<?> findFiltros() {
        return ResponseEntity.status(HttpStatus.OK).body(filtroService.findFiltros());
    }

    @PostMapping("/filtro")
    public ResponseEntity<?> newFiltro(@RequestBody Filtro filtro) {

        try {
            filtroService.newFiltro(filtro);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PatchMapping("/filtro")
    public ResponseEntity<?> updateMensagem(@RequestParam(value = "palavra") String palavra,
                                            @RequestParam(value = "id") int id) {
        try {
            filtroService.updateFiltro(palavra, id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/filtro")
    public ResponseEntity<?> deleteFiltroById(@RequestParam(value = "id") int id) {

        try {
            filtroService.deleteFiltroById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
