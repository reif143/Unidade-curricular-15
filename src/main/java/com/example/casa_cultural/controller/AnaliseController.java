package com.example.casa_cultural.controller;

import com.example.casa_cultural.model.Analise;
import com.example.casa_cultural.service.AnaliseService;
import com.example.casa_cultural.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/analises")
public class AnaliseController {

    @Autowired
    private AnaliseService analiseService;

    @Autowired
    private FilmeService filmeService;

    @GetMapping("/novo/{filmeId}")
    public String novoAnaliseForm(@PathVariable Long filmeId, Model model) {
        Analise analise = new Analise();
        analise.setFilme(filmeService.buscarPorId(filmeId));
        model.addAttribute("analise", analise);
        return "cadastroAnalise";
    }

    @PostMapping("/salvar")
    public String salvarAnalise(@ModelAttribute Analise analise) {
        analiseService.salvarAnalise(analise);
        return "redirect:/analises/detalhes/" + analise.getId();
    }

    @GetMapping("/editar/{id}")
    public String editarAnalise(@PathVariable Long id, Model model) {
        model.addAttribute("analise", analiseService.buscarAnalisePorId(id));
        return "cadastroAnalise";
    }

    @GetMapping("/excluir/{id}")
    public String excluirAnalise(@PathVariable Long id) {
        analiseService.excluirAnalise(id);
        return "redirect:/filmes";
    }

    @GetMapping("/detalhes/{id}")
    public String detalhesAnalise(@PathVariable Long id, Model model) {
        model.addAttribute("analise", analiseService.buscarAnalisePorId(id));
        return "detalhesAnalise";
    }
}
