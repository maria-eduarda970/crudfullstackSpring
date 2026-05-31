package com.exemplo.matriculaservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.matriculaservice.dto.MatriculaDetalhadadto;
import com.exemplo.matriculaservice.model.Matricula;
import com.exemplo.matriculaservice.service.MatriculaService;

/**
 * Controller REST do microserviço de Matrículas.
 *
 * Base URL:
 * http://localhost:8081/api/matriculas
 *
 * Endpoint de comunicação entre microserviços:
 * http://localhost:8081/api/matriculas/{id}/detalhada
 *
 * Exemplo:
 * http://localhost:8081/api/matriculas/1/detalhada
 *
 * Retorna os dados da matrícula juntamente com
 * o nome da pessoa e o nome do curso obtidos
 * via chamadas HTTP aos microserviços externos.
 */

@RestController
@RequestMapping("/api/matriculas")
@CrossOrigin(origins = "*")
public class MatriculaController {

    private final MatriculaService service;

    public MatriculaController(MatriculaService service) {
        this.service = service;
    }

    // =========================
    // LISTAR TODAS AS MATRÍCULAS
    // =========================
    @GetMapping
    public List<Matricula> listarTodas() {
        return service.listarTodas();
    }

    // =========================
    // TESTE DO CONTROLLER ADVICE
    // (FORÇA ERRO PARA VALIDAR JSON PADRÃO)
    // =========================
    @GetMapping("/erro")
    public String erroTeste() {
        throw new RuntimeException("Erro de teste do ControllerAdvice");
    }

    // =========================
    // BUSCAR MATRÍCULA POR ID
    // =========================
    @GetMapping("/{id}")
    public ResponseEntity<Matricula> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // =========================
    // BUSCAR MATRÍCULA DETALHADA
    // (CONSOME OUTROS MICROSSERVIÇOS)
    // =========================
    @GetMapping("/{id}/detalhada")
    public ResponseEntity<MatriculaDetalhadadto> buscarDetalhada(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.buscarDetalhada(id)
        );
    }

    // =========================
    // LISTAR POR PESSOA
    // =========================
    @GetMapping("/pessoa/{pessoaId}")
    public List<Matricula> listarPorPessoa(@PathVariable Long pessoaId) {
        return service.listarPorPessoa(pessoaId);
    }

    // =========================
    // LISTAR POR CURSO
    // =========================
    @GetMapping("/curso/{cursoId}")
    public List<Matricula> listarPorCurso(@PathVariable Long cursoId) {
        return service.listarPorCurso(cursoId);
    }

    // =========================
    // CRIAR MATRÍCULA
    // =========================
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Matricula criar(@RequestBody Matricula matricula) {
        return service.salvar(matricula);
    }

    // =========================
    // ATUALIZAR MATRÍCULA
    // =========================
    @PutMapping("/{id}")
    public ResponseEntity<Matricula> atualizar(
            @PathVariable Long id,
            @RequestBody Matricula matricula) {

        try {
            return ResponseEntity.ok(
                    service.atualizar(id, matricula)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // =========================
    // DESATIVAR MATRÍCULA
    // =========================
    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        try {
            service.desativar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // =========================
    // EXCLUIR MATRÍCULA
    // =========================
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
