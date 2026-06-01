package com.exemplo.pessoaservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.pessoaservice.dto.PessoaDetalhadadto;
import com.exemplo.pessoaservice.model.Pessoa;
import com.exemplo.pessoaservice.repository.PessoaRepository;
import com.exemplo.pessoaservice.service.PessoaService;

@RestController
@RequestMapping("/api/pessoas") // 🔗 URL BASE: http://localhost:8085/api/pessoas
public class PessoaController {

    private final PessoaService service;
    private final PessoaRepository repository;

    public PessoaController(PessoaService service, PessoaRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    // =========================================================
    // ✔ NÍVEL 1 - CRUD BÁSICO
    // =========================================================

    // 🔗 POST: http://localhost:8085/api/pessoas
    @PostMapping
    public ResponseEntity<Pessoa> criar(@RequestBody Pessoa pessoa) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(repository.save(pessoa));
    }

    // 🔗 GET: http://localhost:8085/api/pessoas
    @GetMapping
    public ResponseEntity<List<Pessoa>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    // 🔗 GET: http://localhost:8085/api/pessoas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(
                repository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"))
        );
    }

    // 🔗 DELETE: http://localhost:8085/api/pessoas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // =========================================================
    // ✔ NÍVEL 2 - MICROSERVIÇO (PESSOA + CURSO)
    // =========================================================

    /**
     * 🔥 BUSCA DETALHADA (NÍVEL 2)
     *
     * Exemplo de chamada:
     * GET http://localhost:8085/api/pessoas/1/detalhada
     *Nível 3.1 — Handler Global de Erros
 * Exemplo:
 * GET http://localhost:8085/api/pessoas/999/detalhada
     * Aqui acontece:
     * - busca pessoa no banco
     * - chama curso-service (8083)
     * - monta DTO com nomeCurso
     */
    @GetMapping("/{id}/detalhada")
    public ResponseEntity<PessoaDetalhadadto> buscarDetalhada(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarDetalhada(id));
    }
}