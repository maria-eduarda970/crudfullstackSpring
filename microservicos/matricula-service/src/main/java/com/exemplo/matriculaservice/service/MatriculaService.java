package com.exemplo.matriculaservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.exemplo.matriculaservice.client.Cursoclient;
import com.exemplo.matriculaservice.client.Pessoaclient;
import com.exemplo.matriculaservice.dto.Cursodto;
import com.exemplo.matriculaservice.dto.MatriculaDetalhadadto;
import com.exemplo.matriculaservice.dto.Pessoadto;
import com.exemplo.matriculaservice.model.Matricula;
import com.exemplo.matriculaservice.repository.MatriculaRepository;

/**
 * Camada de negócio do microserviço de Matrículas.
 * Toda a lógica de negócio fica aqui — o controller só delega.
 */
@Service
public class MatriculaService {

    private final MatriculaRepository repository;
    private final Pessoaclient pessoaclient;
    private final Cursoclient cursoclient;

    public MatriculaService(
            MatriculaRepository repository,
            Pessoaclient pessoaclient,
            Cursoclient cursoclient) {

        this.repository = repository;
        this.pessoaclient = pessoaclient;
        this.cursoclient = cursoclient;
    }

    /** Lista todas as matrículas */
    public List<Matricula> listarTodas() {
        return repository.findAll();
    }

    /** Busca uma matrícula pelo ID */
    public Optional<Matricula> buscarPorId(Long id) {
        return repository.findById(id);
    }

    /** Lista matrículas de uma pessoa específica */
    public List<Matricula> listarPorPessoa(Long pessoaId) {
        return repository.findByPessoaId(pessoaId);
    }

    /** Lista matrículas de um curso específico */
    public List<Matricula> listarPorCurso(Long cursoId) {
        return repository.findByCursoId(cursoId);
    }

    /** Cria uma nova matrícula */
    public Matricula salvar(Matricula matricula) {
        return repository.save(matricula);
    }

    /** Atualiza uma matrícula existente */
    public Matricula atualizar(Long id, Matricula dados) {
        Matricula existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada: " + id));

        existente.setPessoaId(dados.getPessoaId());
        existente.setCursoId(dados.getCursoId());
        existente.setDataMatricula(dados.getDataMatricula());
        existente.setAtivo(dados.isAtivo());

        return repository.save(existente);
    }

    /** Desativa uma matrícula (soft delete) */
    public void desativar(Long id) {
        Matricula existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada: " + id));

        existente.setAtivo(false);
        repository.save(existente);
    }

    /** Remove permanentemente uma matrícula */
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    /** Busca matrícula com dados de Pessoa e Curso */
    public MatriculaDetalhadadto buscarDetalhada(Long id) {

        Matricula matricula = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Matrícula não encontrada: " + id));

        Pessoadto pessoa =
                pessoaclient.buscarPessoa(matricula.getPessoaId());

        Cursodto curso =
                cursoclient.buscarCurso(matricula.getCursoId());

        MatriculaDetalhadadto dto =
                new MatriculaDetalhadadto();

        dto.setId(matricula.getId());

        dto.setPessoaId(matricula.getPessoaId());
        dto.setNomePessoa(pessoa.getNome());

        dto.setCursoId(matricula.getCursoId());
        dto.setNomeCurso(curso.getNome());

        dto.setDataMatricula(matricula.getDataMatricula());
        dto.setAtivo(matricula.isAtivo());

        return dto;
    }
}
