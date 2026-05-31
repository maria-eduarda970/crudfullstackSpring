package com.exemplo.turmaservice.controller;

import com.exemplo.turmaservice.model.Turma;
import com.exemplo.turmaservice.service.TurmaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST do microserviço de Turmas.
 *
 * Base URL: http://localhost:8087/api/turmas
 *
 * Endpoints disponíveis:
 *   GET    /api/turmas                 → lista todas
 *   GET    /api/turmas/{id}            → busca por ID
 *   GET    /api/turmas/nome/{nome}     → lista por nome
 *   GET    /api/turmas/ano/{ano}       → lista por ano
 *   POST   /api/turmas                 → cria nova
 *   PUT    /api/turmas/{id}            → atualiza
 *   PATCH  /api/turmas/{id}/desativar  → desativa (soft delete)
 *   DELETE /api/turmas/{id}            → remove permanentemente
 */
@RestController
@RequestMapping("/api/turmas")
@CrossOrigin(origins = "*")
public class TurmaController {

    private final TurmaService service;

    public TurmaController(TurmaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Turma> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public List<Turma> listarPorNome(@PathVariable String nome) {
        return service.listarPorNome(nome);
    }

    @GetMapping("/ano/{ano}")
    public List<Turma> listarPorAno(@PathVariable int ano) {
        return service.listarPorAno(ano);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Turma criar(@RequestBody Turma turma) {
        return service.salvar(turma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizar(@PathVariable Long id,
                                           @RequestBody Turma turma) {
        try {
            return ResponseEntity.ok(service.atualizar(id, turma));
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
