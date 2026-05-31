package com.exemplo.turmaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemplo.turmaservice.model.Turma;

/**
 * Repositório JPA do microserviço de Turmas.
 * Herda todos os métodos CRUD de JpaRepository.
 */
@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

    // Buscar turmas por nome
    List<Turma> findByNome(String nome);

    // Buscar turmas por ano
    List<Turma> findByAno(int ano);

    // Buscar turmas ativas
    List<Turma> findByAtivo(boolean ativo);
}
