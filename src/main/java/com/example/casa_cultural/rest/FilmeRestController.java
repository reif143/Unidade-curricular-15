package com.example.casa_cultural.rest;

import com.example.casa_cultural.model.Filme;
import com.example.casa_cultural.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filmes")
public class FilmeRestController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping
    public List<Filme> listarFilmes() {
        return filmeService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarFilmePorId(@PathVariable Long id) {
        Filme filme = filmeService.buscarPorId(id);
        if (filme != null) {
            return ResponseEntity.ok(filme);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Filme salvarFilme(@RequestBody Filme filme) {
        return filmeService.salvarFilme(filme);
    }

    @PutMapping("/api/filmes/{id}")
    public ResponseEntity<Filme> atualizarFilme(@PathVariable Long id, @RequestBody Filme filme) {
        Filme filmeExistente = filmeService.buscarPorId(id);
        if (filmeExistente != null) {
            filme.setId(id);
            return ResponseEntity.ok(filmeService.salvarFilme(filme));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirFilme(@PathVariable Long id) {
        if (filmeService.buscarPorId(id) != null) {
            filmeService.excluirPorId(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

