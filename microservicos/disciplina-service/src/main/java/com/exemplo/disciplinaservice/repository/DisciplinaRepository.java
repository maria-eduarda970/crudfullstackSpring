package com.exemplo.disciplinaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemplo.disciplinaservice.model.Disciplina;

/**
 * Repositório JPA do microserviço de Disciplinas.
 * Herda todos os métodos CRUD de JpaRepository.
 */
@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    // Buscar disciplinas por nome
    List<Disciplina> findByNome(String nome);

    // Buscar disciplinas ativas
    List<Disciplina> findByAtivo(boolean ativo);
}
