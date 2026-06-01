package com.exemplo.professorservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.exemplo.professorservice.client.Cursoclient;
import com.exemplo.professorservice.client.Turmaclient;
import com.exemplo.professorservice.dto.ProfessorDetalhadodto;
import com.exemplo.professorservice.model.Professor;
import com.exemplo.professorservice.repository.ProfessorRepository;

@Service
public class ProfessorService {

    private final ProfessorRepository repository;
    private final Cursoclient cursoclient;
    private final Turmaclient turmaclient;

    public ProfessorService(ProfessorRepository repository,
                            Cursoclient cursoclient,
                            Turmaclient turmaclient) {
        this.repository = repository;
        this.cursoclient = cursoclient;
        this.turmaclient = turmaclient;
    }

    // =========================================================
    // CRUD NORMAL (NÍVEL 1)
    // =========================================================

    public List<Professor> listarTodos() {
        return repository.findAll();
    }

    public Optional<Professor> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Professor> listarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public List<Professor> listarPorIdade(int idade) {
        return repository.findByIdade(idade);
    }

    public List<Professor> listarPorEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<Professor> listarPorArea(String area) {
        return repository.findByArea(area);
    }

    public Professor salvar(Professor professor) {
        return repository.save(professor);
    }

    public Professor atualizar(Long id, Professor dados) {

        Professor existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado: " + id));

        existente.setNome(dados.getNome());
        existente.setIdade(dados.getIdade());
        existente.setEmail(dados.getEmail());
        existente.setArea(dados.getArea());
        existente.setAtivo(dados.isAtivo());
        existente.setCursoId(dados.getCursoId());
        existente.setTurmaId(dados.getTurmaId());

        return repository.save(existente);
    }

    public void desativar(Long id) {

        Professor existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado: " + id));

        existente.setAtivo(false);

        repository.save(existente);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    // =========================================================
    // ✔ NÍVEL 2 + 3 (MICROSERVIÇOS + FALLBACK)
    // =========================================================

    public ProfessorDetalhadodto buscarDetalhado(Long id) {

        Professor p = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        ProfessorDetalhadodto dto = new ProfessorDetalhadodto();

        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setIdade(p.getIdade());
        dto.setEmail(p.getEmail());
        dto.setArea(p.getArea());
        dto.setAtivo(p.isAtivo());

        dto.setCursoId(p.getCursoId());
        dto.setTurmaId(p.getTurmaId());

        // =====================================================
        // NÍVEL 2 - chamadas entre microserviços
        // =====================================================

        String nomeCurso;
        String nomeTurma;

        try {
            nomeCurso = cursoclient.buscarCurso(p.getCursoId()).getNome();
        } catch (Exception e) {
            nomeCurso = "indisponível"; // NÍVEL 3
        }

        try {
            nomeTurma = turmaclient.buscarTurma(p.getTurmaId()).getNome();
        } catch (Exception e) {
            nomeTurma = "indisponível"; // NÍVEL 3
        }

        dto.setNomeCurso(nomeCurso);
        dto.setNomeTurma(nomeTurma);

        return dto;
    }
}