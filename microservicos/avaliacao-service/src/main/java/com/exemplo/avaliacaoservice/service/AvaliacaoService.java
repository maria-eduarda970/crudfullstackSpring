package com.exemplo.avaliacaoservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.exemplo.avaliacaoservice.model.Avaliacao;
import com.exemplo.avaliacaoservice.repository.AvaliacaoRepository;

/**
 * Camada de negócio do microserviço de Avaliações.
 * Toda a lógica de negócio fica aqui — o controller só delega.
 */
@Service
public class AvaliacaoService {

    private final AvaliacaoRepository repository;

    public AvaliacaoService(AvaliacaoRepository repository) {
        this.repository = repository;
    }

    /** Lista todas as avaliações */
    public List<Avaliacao> listarTodas() {
        return repository.findAll();
    }

    /** Busca uma avaliação pelo ID */
    public Optional<Avaliacao> buscarPorId(Long id) {
        return repository.findById(id);
    }

    /** Lista avaliações de uma pessoa */
    public List<Avaliacao> listarPorPessoa(Long pessoaId) {
        return repository.findByPessoaId(pessoaId);
    }

    /** Lista avaliações de uma disciplina */
    public List<Avaliacao> listarPorDisciplina(Long disciplinaId) {
        return repository.findByDisciplinaId(disciplinaId);
    }

    /** Cria uma nova avaliação */
    public Avaliacao salvar(Avaliacao avaliacao) {
        return repository.save(avaliacao);
    }

    /** Atualiza uma avaliação existente */
    public Avaliacao atualizar(Long id, Avaliacao dados) {
        Avaliacao existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada: " + id));

        existente.setPessoaId(dados.getPessoaId());
        existente.setDisciplinaId(dados.getDisciplinaId());
        existente.setNota(dados.getNota());
        existente.setData(dados.getData());
        existente.setAtivo(dados.isAtivo());

        return repository.save(existente);
    }

    /** Desativa uma avaliação (soft delete) */
    public void desativar(Long id) {
        Avaliacao existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada: " + id));

        existente.setAtivo(false);

        repository.save(existente);
    }

    /** Remove permanentemente uma avaliação */
    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
