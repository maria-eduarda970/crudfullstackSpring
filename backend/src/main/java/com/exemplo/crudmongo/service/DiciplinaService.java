package com.exemplo.crudmongo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.exemplo.crudmongo.Model.Diciplina;
import com.exemplo.crudmongo.Model.Pessoa;
import com.exemplo.crudmongo.repository.DiciplinaRepository;

@Service
public class DiciplinaService {
    private final DiciplinaRepository repository;

    public DiciplinaService(DiciplinaRepository repository) {
        this.repository = repository;
    }

    public List<Diciplina> listarTodas() {
        return repository.findAll();
    }

    public Diciplina salvar(Diciplina diciplina) {
        return repository.save(diciplina);
    }

    public Diciplina atualizar(@PathVariable Long id, Diciplina novaDiciplina) {
        return repository.findById(id).map(p -> {
            p.setNome(novaDiciplina.getNome());
            p.setIdade(novaDiciplina.getIdade());
            p.setEmail(novaDiciplina.getEmail());
            p.setAtivo(novaDiciplina.isAtivo());
            return repository.save(p);
        }).orElseThrow(() -> new RuntimeException("Diciplina não encontrada"));
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
