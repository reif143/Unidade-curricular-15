package com.example.casa_cultural.service;

import com.example.casa_cultural.model.Analise;
import com.example.casa_cultural.repository.AnaliseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnaliseService {

    @Autowired
    private AnaliseRepository analiseRepository;

    public Analise salvarAnalise(Analise analise) {
        return analiseRepository.save(analise);
    }

    public Analise buscarAnalisePorId(Long id) {
        return analiseRepository.findById(id).orElse(null);
    }

    public List<Analise> listarTodasAnalises() {
        return analiseRepository.findAll();
    }

    public void excluirAnalise(Long id) {
        analiseRepository.deleteById(id);
    }

    public Object listarTodas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object buscarPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void excluirPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object buscarTodasAnalises() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

