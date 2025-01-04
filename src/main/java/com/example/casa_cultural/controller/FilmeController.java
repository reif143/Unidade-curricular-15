package com.example.casa_cultural.controller;

import com.example.casa_cultural.model.Preferencia;
import com.example.casa_cultural.model.Filme;
import com.example.casa_cultural.service.FilmeService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @PostMapping("/preferencias")
    public ModelAndView gravaPreferencias(@ModelAttribute Preferencia pref, HttpServletResponse response) {
        Cookie cookiePrefEstilo = new Cookie("pref-estilo", pref.getEstilo());
        System.out.println("Estilo selecionado: " + pref.getEstilo());
        cookiePrefEstilo.setHttpOnly(true);
        cookiePrefEstilo.setMaxAge(86400);
        response.addCookie(cookiePrefEstilo);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/")
    public String index(@CookieValue(name = "pref-estilo", defaultValue = "claro") String tema, Model model) {
        model.addAttribute("css", tema);
        return "index";
    }

    @GetMapping("/listar")
    public String listarFilmes(@CookieValue(name = "pref-estilo", defaultValue = "claro") String tema, Model model) {
        model.addAttribute("filmes", filmeService.listarTodos());
        return "listarFilmes";
    }

    @GetMapping("/novo")
    public String novoFilmeForm(@CookieValue(name = "pref-estilo", defaultValue = "claro") String tema, Model model) {
        model.addAttribute("filme", new Filme());
        return "cadastrarFilmes";
    }

    @PostMapping("/salvar")
    public String salvarFilme(@ModelAttribute Filme filme) {
        filmeService.salvarFilme(filme);
        return "redirect:/filmes";
    }

    @GetMapping("/editar/{id}")
    public String editarFilme(@PathVariable Long id, Model model) {
        model.addAttribute("filme", filmeService.buscarPorId(id));
        return "cadastrarFilmes";
    }

    @GetMapping("/excluir/{id}")
    public String excluirFilme(@PathVariable Long id) {
        filmeService.excluirPorId(id);
        return "redirect:/filmes";
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Filme> atualizarFilme(@PathVariable Long id, @RequestBody Filme filme) {
        Filme filmeAtualizado = filmeService.atualizarFilme(id, filme);
        if (filmeAtualizado != null) {
            return ResponseEntity.ok(filmeAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
