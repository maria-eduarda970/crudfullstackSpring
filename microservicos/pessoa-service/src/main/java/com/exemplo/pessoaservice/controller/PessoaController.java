package com.exemplo.pessoaservice.controller;

import com.exemplo.pessoaservice.model.Pessoa;
import com.exemplo.pessoaservice.service.PessoaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST do microserviço de Pessoas.
 *
 * Base URL: http://localhost:8085/api/pessoas
 *
 * Endpoints disponíveis:
 *   GET    /api/pessoas                    → lista todas
 *   GET    /api/pessoas/{id}               → busca por ID
 *   GET    /api/pessoas/nome/{nome}        → lista por nome
 *   GET    /api/pessoas/email/{email}      → lista por email
 *   POST   /api/pessoas                    → cria nova
 *   PUT    /api/pessoas/{id}               → atualiza
 *   PATCH  /api/pessoas/{id}/desativar     → desativa (soft delete)
 *   DELETE /api/pessoas/{id}               → remove permanentemente
 */
@RestController
@RequestMapping("/api/pessoas")
@CrossOrigin(origins = "*")
public class PessoaController {

    private final PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pessoa> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public List<Pessoa> listarPorNome(@PathVariable String nome) {
        return service.listarPorNome(nome);
    }

    @GetMapping("/email/{email}")
    public List<Pessoa> listarPorEmail(@PathVariable String email) {
        return service.listarPorEmail(email);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa criar(@RequestBody Pessoa pessoa) {
        return service.salvar(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id,
                                            @RequestBody Pessoa pessoa) {
        try {
            return ResponseEntity.ok(service.atualizar(id, pessoa));
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
