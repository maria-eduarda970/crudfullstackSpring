package com.exemplo.avaliacaoservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemplo.avaliacaoservice.model.Avaliacao;

/**
 * Repositório JPA do microserviço de Avaliações.
 * Herda todos os métodos CRUD de JpaRepository.
 */
@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    // Consulta customizada: buscar avaliações de uma pessoa
    List<Avaliacao> findByPessoaId(Long pessoaId);

    // Consulta customizada: buscar avaliações de uma disciplina
    List<Avaliacao> findByDisciplinaId(Long disciplinaId);

    // Consulta customizada: buscar avaliações ativas
    List<Avaliacao> findByAtivo(boolean ativo);

    // Consulta customizada: buscar avaliações por nota
    List<Avaliacao> findByNota(double nota);

    // Consulta customizada: buscar avaliações por data
    List<Avaliacao> findByData(String data);
}