package com.exemplo.disciplinaservice.controller;

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

import com.exemplo.disciplinaservice.dto.DisciplinaDetalhadadto;
import com.exemplo.disciplinaservice.model.Disciplina;
import com.exemplo.disciplinaservice.service.DisciplinaService;

/**
 * Controller REST do microserviço de Disciplinas.
 *
 * Base URL:
 * http://localhost:8084/api/disciplinas
 *
 * Endpoints CRUD:
 * GET    /api/disciplinas
 * GET    /api/disciplinas/{id}
 * GET    /api/disciplinas/nome/{nome}
 * POST   /api/disciplinas
 * PUT    /api/disciplinas/{id}
 * PATCH  /api/disciplinas/{id}/desativar
 * DELETE /api/disciplinas/{id}
 *
 * Nível 2 — Comunicação entre microserviços
 *
 * Exemplo:
 * GET http://localhost:8084/api/disciplinas/1/detalhado
 *
 * Nível 3.1 — Handler Global de Erros
 * Exemplo:
 * GET http://localhost:8084/api/disciplinas/999/detalhado
 *
 * Retorno:
 * {
 *   "status": 400,
 *   "mensagem": "Disciplina não encontrada",
 *   "timestamp": "2026-06-01T10:00:00"
 * }
 *
 * Nível 3.2 — Fallback
 * Se curso-service estiver indisponível:
 *
 * GET http://localhost:8084/api/disciplinas/1/detalhado
 *
 * Retorna:
 * {
 *   "nomeCurso": "indisponível"
 * }
 */
@RestController
@RequestMapping("/api/disciplinas")
@CrossOrigin(origins = "*")
public class DisciplinaController {

    private final DisciplinaService service;

    public DisciplinaController(DisciplinaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Disciplina> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Nível 2
     * GET /api/disciplinas/{id}/detalhado
     */
    @GetMapping("/{id}/detalhado")
    public ResponseEntity<DisciplinaDetalhadadto> buscarDetalhado(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarDetalhado(id));
    }

    @GetMapping("/nome/{nome}")
    public List<Disciplina> listarPorNome(@PathVariable String nome) {
        return service.listarPorNome(nome);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Disciplina criar(@RequestBody Disciplina disciplina) {
        return service.salvar(disciplina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> atualizar(
            @PathVariable Long id,
            @RequestBody Disciplina disciplina) {

        try {
            return ResponseEntity.ok(service.atualizar(id, disciplina));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {

        try {
            service.desativar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
