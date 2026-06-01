package com.exemplo.turmaservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.exemplo.turmaservice.client.Professorclient;
import com.exemplo.turmaservice.dto.Professordto;
import com.exemplo.turmaservice.dto.TurmaDetalhadadto;
import com.exemplo.turmaservice.model.Turma;
import com.exemplo.turmaservice.repository.TurmaRepository;

@Service
public class TurmaService {

    private final TurmaRepository repository;
    private final Professorclient professorclient;

    public TurmaService(TurmaRepository repository,
                        Professorclient professorclient) {
        this.repository = repository;
        this.professorclient = professorclient;
    }

    // =========================================================
    // ✔ NÍVEL 1 - CRUD
    // =========================================================

    public List<Turma> listarTodas() {
        return repository.findAll();
    }

    public Optional<Turma> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Turma> listarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public List<Turma> listarPorAno(int ano) {
        return repository.findByAno(ano);
    }

    public Turma salvar(Turma turma) {
        return repository.save(turma);
    }

    public Turma atualizar(Long id, Turma dados) {

        Turma existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada: " + id));

        existente.setNome(dados.getNome());
        existente.setAno(dados.getAno());
        existente.setAtivo(dados.isAtivo());
        existente.setProfessorId(dados.getProfessorId());
        existente.setNomeProfessor(dados.getNomeProfessor());

        return repository.save(existente);
    }

    public void desativar(Long id) {

        Turma existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada: " + id));

        existente.setAtivo(false);

        repository.save(existente);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    // =========================================================
    // ✔ NÍVEL 2 + 3 - MICROSERVIÇOS (DETALHADO)
    // =========================================================

    /**
     * GET DETALHADO (Turma + Professor via HTTP)
     * com fallback (Nível 3)
     */
    public TurmaDetalhadadto buscarDetalhada(Long id) {

        Turma turma = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada: " + id));

        TurmaDetalhadadto dto = new TurmaDetalhadadto();

        dto.setId(turma.getId());
        dto.setNome(turma.getNome());
        dto.setAno(turma.getAno());
        dto.setAtivo(turma.isAtivo());
        dto.setProfessorId(turma.getProfessorId());

        // 🔗 chamada microserviço professor
        try {
            Professordto professor = professorclient.buscarProfessor(turma.getProfessorId());

            if (professor != null && professor.getNome() != null) {
                dto.setNomeProfessor(professor.getNome());
            } else {
                dto.setNomeProfessor("indisponível");
            }

        } catch (Exception e) {
            dto.setNomeProfessor("indisponível");
        }

        return dto;
    }
}