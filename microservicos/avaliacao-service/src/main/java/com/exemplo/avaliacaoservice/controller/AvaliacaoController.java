package com.exemplo.avaliacaoservice.controller;

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

import com.exemplo.avaliacaoservice.dto.AvaliacaoDetalhadadto;
import com.exemplo.avaliacaoservice.model.Avaliacao;
import com.exemplo.avaliacaoservice.service.AvaliacaoService;

/**
 * Controller REST do microserviço de Avaliações.
 *
 * Base URL:
 * http://localhost:8082/api/avaliacoes
 *
 * Endpoint de comunicação entre microserviços:
 * http://localhost:8082/api/avaliacoes/{id}/detalhada
 *
 * Exemplo:
 * http://localhost:8082/api/avaliacoes/1/detalhada
 *
 * Retorna os dados da avaliação juntamente com
 * o nome da pessoa e o nome da disciplina obtidos
 * via chamadas HTTP aos microserviços externos. 
 * 
 * 
 *
 * Endpoints disponíveis:
 *   GET    /api/avaliacoes
 *   GET    /api/avaliacoes/{id}
 *   GET    /api/avaliacoes/{id}/detalhada
 *   GET    /api/avaliacoes/pessoa/{id}
 *   GET    /api/avaliacoes/disciplina/{id}
 *   POST   /api/avaliacoes
 *   PUT    /api/avaliacoes/{id}
 *   PATCH  /api/avaliacoes/{id}/desativar
 *   DELETE /api/avaliacoes/{id}
 */
/**
 * Nível 3.1 - Handler Global de Erros
 *
 * Classe responsável:
 * GlobalExceptionHandler
 *
 * URL para teste:
 * http://localhost:8082/api/avaliacoes/999/detalhada
 */
/**
 * Nível 3.2 - Fallback entre microserviços
 *
 * Endpoint de teste:
 * http://localhost:8082/api/avaliacoes/1/detalhada
 *
 * Se pessoa-service ou disciplina-service
 * estiver indisponível, a API continua
 * respondendo normalmente retornando:
 *
 * nomePessoa = "indisponível"
 * ou
 * nomeDisciplina = "indisponível"
 *
 * sem derrubar o avaliacao-service.
 */

@RestController
@RequestMapping("/api/avaliacoes")
@CrossOrigin(origins = "*")
public class AvaliacaoController {

    private final AvaliacaoService service;

    public AvaliacaoController(AvaliacaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Avaliacao> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Exemplo:
     * http://localhost:8082/api/avaliacoes/1/detalhada
     */
    @GetMapping("/{id}/detalhada")
    public ResponseEntity<AvaliacaoDetalhadadto> buscarDetalhada(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.buscarDetalhada(id)
        );
    }

    @GetMapping("/pessoa/{pessoaId}")
    public List<Avaliacao> listarPorPessoa(@PathVariable Long pessoaId) {
        return service.listarPorPessoa(pessoaId);
    }

    @GetMapping("/disciplina/{disciplinaId}")
    public List<Avaliacao> listarPorDisciplina(@PathVariable Long disciplinaId) {
        return service.listarPorDisciplina(disciplinaId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Avaliacao criar(@RequestBody Avaliacao avaliacao) {
        return service.salvar(avaliacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> atualizar(
            @PathVariable Long id,
            @RequestBody Avaliacao avaliacao) {

        try {
            return ResponseEntity.ok(
                    service.atualizar(id, avaliacao)
            );
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
