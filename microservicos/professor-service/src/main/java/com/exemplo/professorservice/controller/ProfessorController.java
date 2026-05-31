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

import com.exemplo.professorservice.model.Professor;
import com.exemplo.professorservice.service.ProfessorService;

/**
 * Controller REST do microserviço de Professores.
 *
 * Base URL: http://localhost:8086/api/professores
 *
 * Endpoints disponíveis:
 *   GET    /api/professores                 → lista todos
 *   GET    /api/professores/{id}            → busca por ID
 *   GET    /api/professores/nome/{nome}     → lista por nome
 *   GET    /api/professores/email/{email}   → lista por email
 *   GET    /api/professores/area/{area}     → lista por área
 *   POST   /api/professores                 → cria novo
 *   PUT    /api/professores/{id}            → atualiza
 *   PATCH  /api/professores/{id}/desativar  → desativa (soft delete)
 *   DELETE /api/professores/{id}            → remove permanentemente
 */
@RestController
@RequestMapping("/api/professores")
@CrossOrigin(origins = "*")
public class ProfessorController {

    private final ProfessorService service;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Professor> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public List<Professor> listarPorNome(@PathVariable String nome) {
        return service.listarPorNome(nome);
    }

    @GetMapping("/email/{email}")
    public List<Professor> listarPorEmail(@PathVariable String email) {
        return service.listarPorEmail(email);
    }

    @GetMapping("/area/{area}")
    public List<Professor> listarPorArea(@PathVariable String area) {
        return service.listarPorArea(area);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Professor criar(@RequestBody Professor professor) {
        return service.salvar(professor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizar(@PathVariable Long id,
    @RequestBody Professor professor) {
        try {
            return ResponseEntity.ok(service.atualizar(id, professor));
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