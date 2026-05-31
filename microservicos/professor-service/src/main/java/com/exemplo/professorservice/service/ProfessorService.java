package com.exemplo.professorservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.exemplo.professorservice.model.Professor;
import com.exemplo.professorservice.repository.ProfessorRepository;

/**
 * Camada de negócio do microserviço de Professores.
 * Toda a lógica de negócio fica aqui — o controller só delega.
 */
@Service
public class ProfessorService {

    private final ProfessorRepository repository;

    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }

    /** Lista todos os professores */
    public List<Professor> listarTodos() {
        return repository.findAll();
    }

    /** Busca um professor pelo ID */
    public Optional<Professor> buscarPorId(Long id) {
        return repository.findById(id);
    }

    /** Lista professores por nome */
    public List<Professor> listarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    /** Lista professores por idade */
    public List<Professor> listarPorIdade(int idade) {
        return repository.findByIdade(idade);
    }

    /** Lista professores por email */
    public List<Professor> listarPorEmail(String email) {
        return repository.findByEmail(email);
    }

    /** Lista professores por área */
    public List<Professor> listarPorArea(String area) {
        return repository.findByArea(area);
    }

    /** Cria um novo professor */
    public Professor salvar(Professor professor) {
        return repository.save(professor);
    }

    /** Atualiza um professor existente */
    public Professor atualizar(Long id, Professor dados) {
        Professor existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado: " + id));

        existente.setNome(dados.getNome());
        existente.setIdade(dados.getIdade());
        existente.setEmail(dados.getEmail());
        existente.setArea(dados.getArea());
        existente.setAtivo(dados.isAtivo());

        return repository.save(existente);
    }

    /** Desativa um professor (soft delete) */
    public void desativar(Long id) {
        Professor existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado: " + id));

        existente.setAtivo(false);

        repository.save(existente);
    }

    /** Remove permanentemente um professor */
    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
