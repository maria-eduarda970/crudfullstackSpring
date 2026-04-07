package com.exemplo.crudmongo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.exemplo.crudmongo.Model.Curso;
import com.exemplo.crudmongo.repository.CursoRepository;

@Service
public class CursoService {
    private final CursoRepository repository; // Repositório para acesso ao banco de dados
    
    /**
     * Injeta o repositório PessoaRepository via construtor.
     */
    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    /**
     * Retorna todos os cursos cadastradas no banco de dados.
     * @return
     */
    public List<Curso> listarTodas() {
        return repository.findAll();
    }

    /**
     * Salva uma nova pessoa no banco de dados.
     * @param curso Objeto Curso a ser salvo
     * @return Curso salva
     */
    public Curso salvar(Curso curso) {
        return repository.save(curso);
    }

    /**
     * Atualiza uma pessoa existente pelo ID.
     * @param id Identificador de curso a ser atualizada
     * @param novaCurso Dados atualizados de curso
     * @return Curso atualizada
     */
    public Curso atualizar(@PathVariable Long id,  Curso novoCurso) {
        return repository.findById(id).map(c -> {
            c.setId(id);
            c.setNome(novoCurso.getNome());
            c.setAtivo(novoCurso.isAtivo());
            return repository.save(c);
        }).orElseThrow(() -> new RuntimeException("Curso não encontrada"));
    }

    /**
     * Exclui um curso pelo ID.
     * @param id Identificador da pessoa a ser excluída
     */
    public void excluir(Long id) {
        repository.deleteById(id);
    }
}