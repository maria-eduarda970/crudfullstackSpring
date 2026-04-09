package com.exemplo.crudmongo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.exemplo.crudmongo.Model.Matricula;
import com.exemplo.crudmongo.Model.Pessoa;
import com.exemplo.crudmongo.repository.MatriculaRepository;

@Service
public class MatriculaService {
    private final MatriculaRepository repository;

    public MatriculaService(MatriculaRepository repository) {
        this.repository = repository;
    }

    public List<Matricula> listarTodas() {
        return repository.findAll();
    }

    public Matricula salvar(Matricula diciplina) {
        return repository.save(diciplina);
    }

    public Matricula atualizar(@PathVariable Long id, Matricula novaMatricula) {
        return repository.findById(id).map(p -> {
            p.setNome(novaMatricula.getNome());
            p.setIdade(novaMatricula.getIdade());
            p.setEmail(novaMatricula.getEmail());
            p.setAtivo(novaMatricula.isAtivo());
            return repository.save(p);
        }).orElseThrow(() -> new RuntimeException("Matricula não encontrada"));
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
