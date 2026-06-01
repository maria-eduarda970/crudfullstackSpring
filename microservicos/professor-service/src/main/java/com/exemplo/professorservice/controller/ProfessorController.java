package com.exemplo.professorservice.controller;

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

import com.exemplo.professorservice.dto.ProfessorDetalhadodto;
import com.exemplo.professorservice.model.Professor;
import com.exemplo.professorservice.service.ProfessorService;

@RestController
@RequestMapping("/api/professores")
@CrossOrigin(origins = "*")
public class ProfessorController {

    private final ProfessorService service;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    // =========================================================
    // ✔ NÍVEL 1 - CRUD BÁSICO
    // Base URL: http://localhost:8086/api/professores
    // =========================================================

    /**
     * GET http://localhost:8086/api/professores
     *  Nível 2 — Comunicação entre microserviços
 * URL:
GET http://localhost:8086/api/professores/1/detalhado
 *
 * Nível 3.1 — Handler Global de Erros
 * Exemplo:
 * GET  http://localhost:8086/api/professores/999/detalhado
 *
     */
    @GetMapping
    public List<Professor> listarTodos() {
        return service.listarTodos();
    }

    /**
     * GET http://localhost:8086/api/professores/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * GET http://localhost:8086/api/professores/nome/{nome}
     */
    @GetMapping("/nome/{nome}")
    public List<Professor> listarPorNome(@PathVariable String nome) {
        return service.listarPorNome(nome);
    }

    /**
     * GET http://localhost:8086/api/professores/email/{email}
     */
    @GetMapping("/email/{email}")
    public List<Professor> listarPorEmail(@PathVariable String email) {
        return service.listarPorEmail(email);
    }

    /**
     * GET http://localhost:8086/api/professores/area/{area}
     */
    @GetMapping("/area/{area}")
    public List<Professor> listarPorArea(@PathVariable String area) {
        return service.listarPorArea(area);
    }

    /**
     * POST http://localhost:8086/api/professores
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Professor criar(@RequestBody Professor professor) {
        return service.salvar(professor);
    }

    /**
     * PUT http://localhost:8086/api/professores/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizar(@PathVariable Long id,
                                                @RequestBody Professor professor) {
        try {
            return ResponseEntity.ok(service.atualizar(id, professor));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * PATCH http://localhost:8086/api/professores/{id}/desativar
     */
    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        try {
            service.desativar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE http://localhost:8086/api/professores/{id}
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    // =========================================================
    // ✔ NÍVEL 2 + 3 - MICROSERVIÇOS (CURSO + TURMA)
    // =========================================================

    /**
     * 🔥 DETALHADO (NÍVEL 2 + 3)
     *
     * GET http://localhost:8086/api/professores/{id}/detalhado
     *
     * Retorna:
     * - dados do professor
     * - nome do curso (curso-service)
     * - nome da turma (turma-service)
     * - fallback "indisponível" se algum serviço cair
     */
    @GetMapping("/{id}/detalhado")
    public ResponseEntity<ProfessorDetalhadodto> buscarDetalhado(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarDetalhado(id));
    }
}