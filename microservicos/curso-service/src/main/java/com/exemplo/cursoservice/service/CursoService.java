package com.exemplo.cursoservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.exemplo.cursoservice.client.Professorclient;
import com.exemplo.cursoservice.dto.CursoDetalhadadto;
import com.exemplo.cursoservice.dto.Professordto;
import com.exemplo.cursoservice.model.Curso;
import com.exemplo.cursoservice.repository.CursoRepository;

@Service
public class CursoService {

    private final CursoRepository repository;
    private final Professorclient professorClient;

    public CursoService(CursoRepository repository,
                        Professorclient professorclient) {

        this.repository = repository;
        this.professorClient = professorclient;
    }

    /** Lista todos os cursos */
    public List<Curso> listarTodos() {
        return repository.findAll();
    }

    /** Busca curso por ID */
    public Optional<Curso> buscarPorId(Long id) {
        return repository.findById(id);
    }

    /** Cria novo curso */
    public Curso salvar(Curso curso) {
        return repository.save(curso);
    }

    /** Atualiza curso existente */
    public Curso atualizar(Long id, Curso dados) {

        Curso existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        existente.setNome(dados.getNome());
        existente.setCargaHoraria(dados.getCargaHoraria());
        existente.setProfessorId(dados.getProfessorId());
        existente.setAtivo(dados.isAtivo());

        return repository.save(existente);
    }

    /** Soft delete */
    public void desativar(Long id) {

        Curso curso = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        curso.setAtivo(false);

        repository.save(curso);
    }

    /** Exclusão permanente */
    public void excluir(Long id) {

        Curso curso = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        repository.delete(curso);
    }

    /**
     * GET /api/cursos/{id}/detalhado
     *
     * Busca o curso e consulta o professor-service
     * para obter o nome do professor responsável.
     */
    public CursoDetalhadadto buscarDetalhado(Long id) {

        Curso curso = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        Professordto professor =
                professorClient.buscarProfessor(curso.getProfessorId());

        CursoDetalhadadto dto = new CursoDetalhadadto();

        dto.setId(curso.getId());
        dto.setNome(curso.getNome());
        dto.setCargaHoraria(curso.getCargaHoraria());

        dto.setProfessorId(curso.getProfessorId());
        dto.setNomeProfessor(professor.getNome());

        dto.setAtivo(curso.isAtivo());

        return dto;
    }
}