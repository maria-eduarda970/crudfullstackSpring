package com.exemplo.crudmongo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import com.exemplo.crudmongo.Model.Disciplina;
import com.exemplo.crudmongo.repository.DisciplinaRepository;

@Service
public class DisciplinaService {
    private final DisciplinaRepository repository;

    public DisciplinaService(DisciplinaRepository repository) {
        this.repository = repository;
    }

    public List<Disciplina> listarTodas() {
        return repository.findAll();
    }

    public Disciplina salvar( Disciplina disciplina) {
        return repository.save(disciplina);
    }

    public Disciplina atualizar(@PathVariable Long id, Disciplina novaDisciplina) {
        return repository.findById(id).map(d -> {
            d.setId(id);
            return repository.save(d);
        }).orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}