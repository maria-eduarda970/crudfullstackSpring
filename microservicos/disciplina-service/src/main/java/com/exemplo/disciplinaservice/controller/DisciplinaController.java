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

import com.exemplo.disciplinaservice.model.Disciplina;
import com.exemplo.disciplinaservice.service.DisciplinaService;

/**
 * Controller REST do microserviço de Disciplinas.
 *
 * Base URL: http://localhost:8084/api/disciplinas
 *
 * Endpoints disponíveis:
 *   GET    /api/disciplinas                 → lista todas
 *   GET    /api/disciplinas/{id}            → busca por ID
 *   GET    /api/disciplinas/nome/{nome}     → lista por nome
 *   POST   /api/disciplinas                 → cria nova
 *   PUT    /api/disciplinas/{id}            → atualiza
 *   PATCH  /api/disciplinas/{id}/desativar  → desativa (soft delete)
 *   DELETE /api/disciplinas/{id}            → remove permanentemente
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
    public ResponseEntity<Disciplina> atualizar(@PathVariable Long id,
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
