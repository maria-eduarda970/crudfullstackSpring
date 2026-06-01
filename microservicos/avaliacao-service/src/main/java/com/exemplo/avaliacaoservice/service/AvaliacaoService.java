package com.exemplo.avaliacaoservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.exemplo.avaliacaoservice.client.Disciplinaclient;
import com.exemplo.avaliacaoservice.client.Pessoaclient;
import com.exemplo.avaliacaoservice.dto.AvaliacaoDetalhadadto;
import com.exemplo.avaliacaoservice.dto.Disciplinadto;
import com.exemplo.avaliacaoservice.dto.Pessoadto;
import com.exemplo.avaliacaoservice.model.Avaliacao;
import com.exemplo.avaliacaoservice.repository.AvaliacaoRepository;

/**
 * Camada de negócio do microserviço de Avaliações.
 * Toda a lógica de negócio fica aqui — o controller só delega.
 */
@Service
public class AvaliacaoService {

    private final AvaliacaoRepository repository;
    private final Pessoaclient pessoaclient;
    private final Disciplinaclient disciplinaclient;

    public AvaliacaoService(
            AvaliacaoRepository repository,
            Pessoaclient pessoaclient,
            Disciplinaclient disciplinaclient) {

        this.repository = repository;
        this.pessoaclient = pessoaclient;
        this.disciplinaclient = disciplinaclient;
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

    /** Busca avaliação detalhada */
    public AvaliacaoDetalhadadto buscarDetalhada(Long id) {

        Avaliacao avaliacao = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada"));

        Pessoadto pessoa =
                pessoaclient.buscarPessoa(avaliacao.getPessoaId());

        Disciplinadto disciplina =
                disciplinaclient.buscarDisciplina(avaliacao.getDisciplinaId());

        AvaliacaoDetalhadadto dto = new AvaliacaoDetalhadadto();

        dto.setId(avaliacao.getId());

        dto.setPessoaId(avaliacao.getPessoaId());
        dto.setNomePessoa(pessoa.getNome());

        dto.setDisciplinaId(avaliacao.getDisciplinaId());
        dto.setNomeDisciplina(disciplina.getNome());

        dto.setNota(avaliacao.getNota());
        dto.setData(avaliacao.getData());

        dto.setAtivo(avaliacao.isAtivo());

        return dto;
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
