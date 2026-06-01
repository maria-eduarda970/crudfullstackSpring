package com.exemplo.turmaservice.controller;

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

import com.exemplo.turmaservice.dto.TurmaDetalhadadto;
import com.exemplo.turmaservice.model.Turma;
import com.exemplo.turmaservice.service.TurmaService;

@RestController
@RequestMapping("/api/turmas")
@CrossOrigin(origins = "*")
public class TurmaController {

    private final TurmaService service;

    public TurmaController(TurmaService service) {
        this.service = service;
    }

    // =========================================================
    // ✔ NÍVEL 1 - CRUD
    // Base URL: http://localhost:8087/api/turmas
    // =========================================================

    /**
     * 
    /**
     * GET http://localhost:8087/api/turmas
     *  Nível 2 — Comunicação entre microserviços
 * URL:
GET http://localhost:8087/api/turmas/1/detalhado
 *
 * Nível 3.1 — Handler Global de Erros
 * Exemplo:
 * GET  http://localhost:8087/api/turmas/999/detalhado
 *
     * GET http://localhost:8087/api/turmas
     */
    @GetMapping
    public List<Turma> listarTodas() {
        return service.listarTodas();
    }

    /**
     * GET http://localhost:8087/api/turmas/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * GET http://localhost:8087/api/turmas/nome/{nome}
     */
    @GetMapping("/nome/{nome}")
    public List<Turma> listarPorNome(@PathVariable String nome) {
        return service.listarPorNome(nome);
    }

    /**
     * GET http://localhost:8087/api/turmas/ano/{ano}
     */
    @GetMapping("/ano/{ano}")
    public List<Turma> listarPorAno(@PathVariable int ano) {
        return service.listarPorAno(ano);
    }

    /**
     * POST http://localhost:8087/api/turmas
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Turma criar(@RequestBody Turma turma) {
        return service.salvar(turma);
    }

    /**
     * PUT http://localhost:8087/api/turmas/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizar(@PathVariable Long id,
    @RequestBody Turma turma) {
        try {
            return ResponseEntity.ok(service.atualizar(id, turma));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * PATCH http://localhost:8087/api/turmas/{id}/desativar
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
     * DELETE http://localhost:8087/api/turmas/{id}
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    // =========================================================
    // ✔ NÍVEL 2 + 3 - MICROSERVIÇOS (PROFESSOR)
    // =========================================================

    /**
     * 🔥 DETALHADO (NÍVEL 2 + 3)
     *
     * GET http://localhost:8087/api/turmas/{id}/detalhado
     *
     * Retorna:
     * - dados da turma
     * - nome do professor (via professor-service 8086)
     * - fallback "indisponível" se o serviço cair (Nível 3)
     */
    @GetMapping("/{id}/detalhado")
    public ResponseEntity<TurmaDetalhadadto> buscarDetalhada(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarDetalhada(id));
    }
}
