package com.exemplo.pessoaservice.service;

import org.springframework.stereotype.Service;

import com.exemplo.pessoaservice.client.Cursoclient;
import com.exemplo.pessoaservice.dto.PessoaDetalhadadto;
import com.exemplo.pessoaservice.model.Pessoa;
import com.exemplo.pessoaservice.repository.PessoaRepository;

@Service
public class PessoaService {

    private final PessoaRepository repository;
    private final Cursoclient cursoclient;

    public PessoaService(PessoaRepository repository,
    Cursoclient cursoclient) {
        this.repository = repository;
        this.cursoclient = cursoclient;
    }

    public PessoaDetalhadadto buscarDetalhada(Long id) {

        Pessoa p = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        PessoaDetalhadadto dto = new PessoaDetalhadadto();

        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setIdade(p.getIdade());
        dto.setEmail(p.getEmail());
        dto.setTelefone(p.getTelefone());
        dto.setAtivo(p.isAtivo());

        dto.setCursoId(p.getCursoId());

        // NÍVEL 2: chamada entre serviços
        String nomeCurso = cursoclient.buscarNomeCurso(p.getCursoId());

        dto.setNomeCurso(nomeCurso);

        return dto;
    }
}