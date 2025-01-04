package com.example.casa_cultural.rest;

import com.example.casa_cultural.model.Analise;
import com.example.casa_cultural.service.AnaliseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analises")
public class AnaliseRestController {

    @Autowired
    private AnaliseService analiseService;

    @GetMapping
    public List<Analise> listarAnalises() {
        return analiseService.listarTodasAnalises();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Analise> buscarAnalisePorId(@PathVariable Long id) {
        Analise analise = analiseService.buscarAnalisePorId(id);
        if (analise != null) {
            return ResponseEntity.ok(analise);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Analise salvarAnalise(@RequestBody Analise analise) {
        return analiseService.salvarAnalise(analise);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Analise> atualizarAnalise(@PathVariable Long id, @RequestBody Analise analise) {
        Analise analiseExistente = analiseService.buscarAnalisePorId(id);
        if (analiseExistente != null) {
            analise.setId(id);
            return ResponseEntity.ok(analiseService.salvarAnalise(analise));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAnalise(@PathVariable Long id) {
        if (analiseService.buscarAnalisePorId(id) != null) {
            analiseService.excluirAnalise(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
