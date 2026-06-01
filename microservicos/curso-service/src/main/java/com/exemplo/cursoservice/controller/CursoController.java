package com.exemplo.cursoservice.controller;

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

import com.exemplo.cursoservice.dto.CursoDetalhadadto;
import com.exemplo.cursoservice.model.Curso;
import com.exemplo.cursoservice.service.CursoService;

/**
 * Controller REST — curso-service
 *
 * Base URL:
 * http://localhost:8083/api/cursos
 *
 * Endpoints CRUD:
 * GET    /api/cursos
 * GET    /api/cursos/{id}
 * POST   /api/cursos
 * PUT    /api/cursos/{id}
 * PATCH  /api/cursos/{id}/desativar
 * DELETE /api/cursos/{id}
 *
 * Nível 2 — Comunicação entre microserviços
 * URL:
 *
 * GET http://localhost:8083/api/cursos/1/detalhado
 *
 * Nível 3.1 — Handler Global de Erros
 * Exemplo:
 * GET http://localhost:8083/api/cursos/999/detalhado
 *
 * Retorno:
 * {
 *   "status": 400,
 *   "mensagem": "Curso não encontrado",
 *   "timestamp": "2026-06-01T10:00:00"
 * }
 *
 * Nível 3.2 — Fallback
 * Se professor-service estiver indisponível:
 *
 * GET http://localhost:8083/api/cursos/1/detalhado
 *
 * Retorna:
 * {
 *   "nomeProfessor": "indisponível"
 * }
 */
@RestController
@RequestMapping("/api/cursos")
@CrossOrigin(origins = "*")
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Curso> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Nível 2
     * GET /api/cursos/{id}/detalhado
     */
    @GetMapping("/{id}/detalhado")
    public ResponseEntity<CursoDetalhadadto> buscarDetalhado(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarDetalhado(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Curso criar(@RequestBody Curso curso) {
        return service.salvar(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizar(
            @PathVariable Long id,
            @RequestBody Curso curso) {

        try {
            return ResponseEntity.ok(service.atualizar(id, curso));
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
