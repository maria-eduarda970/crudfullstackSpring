package com.exemplo.disciplinaservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.exemplo.disciplinaservice.client.Cursoclient;
import com.exemplo.disciplinaservice.dto.Cursodto;
import com.exemplo.disciplinaservice.dto.DisciplinaDetalhadadto;
import com.exemplo.disciplinaservice.model.Disciplina;
import com.exemplo.disciplinaservice.repository.DisciplinaRepository;

/**
 * Camada de negócio do microserviço de Disciplinas.
 * Toda a lógica de negócio fica aqui — o controller só delega.
 */
@Service
public class DisciplinaService {

    private final DisciplinaRepository repository;
    private final Cursoclient cursoclient;

    public DisciplinaService(
            DisciplinaRepository repository,
            Cursoclient cursoclient) {

        this.repository = repository;
        this.cursoclient = cursoclient;
    }

    /** Lista todas as disciplinas */
    public List<Disciplina> listarTodas() {
        return repository.findAll();
    }

    /** Busca uma disciplina pelo ID */
    public Optional<Disciplina> buscarPorId(Long id) {
        return repository.findById(id);
    }

    /** Lista disciplinas por nome */
    public List<Disciplina> listarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    /** Cria uma nova disciplina */
    public Disciplina salvar(Disciplina disciplina) {
        return repository.save(disciplina);
    }

    /** Atualiza uma disciplina existente */
    public Disciplina atualizar(Long id, Disciplina dados) {

        Disciplina existente = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Disciplina não encontrada"));

        existente.setNome(dados.getNome());
        existente.setCursoId(dados.getCursoId());
        existente.setAtivo(dados.isAtivo());

        return repository.save(existente);
    }

    /** Desativa uma disciplina (soft delete) */
    public void desativar(Long id) {

        Disciplina existente = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Disciplina não encontrada"));

        existente.setAtivo(false);

        repository.save(existente);
    }

    /** Remove permanentemente uma disciplina */
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    /**
     * Nível 2 — Comunicação entre Microserviços
     * GET /api/disciplinas/{id}/detalhado
     */
    public DisciplinaDetalhadadto buscarDetalhado(Long id) {

        Disciplina disciplina = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Disciplina não encontrada"));

        Cursodto curso =
                cursoclient.buscarCurso(disciplina.getCursoId());

        DisciplinaDetalhadadto dto =
                new DisciplinaDetalhadadto();

        dto.setId(disciplina.getId());
        dto.setNome(disciplina.getNome());

        dto.setCursoId(disciplina.getCursoId());
        dto.setNomeCurso(curso.getNome());

        dto.setAtivo(disciplina.isAtivo());

        return dto;
    }
}
