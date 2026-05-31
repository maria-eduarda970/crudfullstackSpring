package com.exemplo.turmaservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.exemplo.turmaservice.model.Turma;
import com.exemplo.turmaservice.repository.TurmaRepository;

/**
 * Camada de negócio do microserviço de Turmas.
 * Toda a lógica de negócio fica aqui — o controller só delega.
 */
@Service
public class TurmaService {

    private final TurmaRepository repository;

    public TurmaService(TurmaRepository repository) {
        this.repository = repository;
    }

    /** Lista todas as turmas */
    public List<Turma> listarTodas() {
        return repository.findAll();
    }

    /** Busca uma turma pelo ID */
    public Optional<Turma> buscarPorId(Long id) {
        return repository.findById(id);
    }

    /** Lista turmas por nome */
    public List<Turma> listarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    /** Lista turmas por ano */
    public List<Turma> listarPorAno(int ano) {
        return repository.findByAno(ano);
    }

    /** Cria uma nova turma */
    public Turma salvar(Turma turma) {
        return repository.save(turma);
    }

    /** Atualiza uma turma existente */
    public Turma atualizar(Long id, Turma dados) {
        Turma existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada: " + id));

        existente.setNome(dados.getNome());
        existente.setAno(dados.getAno());
        existente.setAtivo(dados.isAtivo());

        return repository.save(existente);
    }

    /** Desativa uma turma (soft delete) */
    public void desativar(Long id) {
        Turma existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada: " + id));

        existente.setAtivo(false);

        repository.save(existente);
    }

    /** Remove permanentemente uma turma */
    public void excluir(Long id) {
        repository.deleteById(id);
    }
}