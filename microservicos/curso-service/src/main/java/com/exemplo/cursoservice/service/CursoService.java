package com.exemplo.cursoservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.exemplo.cursoservice.model.Curso;
import com.exemplo.cursoservice.repository.CursoRepository;

@Service
public class CursoService {

    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    public List<Curso> listarTodos() {
        return repository.findAll();
    }

    public Optional<Curso> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Curso salvar(Curso curso) {
        return repository.save(curso);
    }

    public Curso atualizar(Long id, Curso curso) {
        curso.setId(id);
        return repository.save(curso);
    }

    public void desativar(Long id) {
        Curso curso = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        curso.setAtivo(false);
        repository.save(curso);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}