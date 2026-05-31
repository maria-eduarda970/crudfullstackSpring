package com.exemplo.professorservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemplo.professorservice.model.Professor;

/**
 * Repositório JPA do microserviço de Professores.
 * Herda todos os métodos CRUD de JpaRepository.
 */
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    // Buscar professores por nome
    List<Professor> findByNome(String nome);

    // Buscar professores por idade
    List<Professor> findByIdade(int idade);

    // Buscar professores por email
    List<Professor> findByEmail(String email);

    // Buscar professores por área
    List<Professor> findByArea(String area);

    // Buscar professores ativos
    List<Professor> findByAtivo(boolean ativo);
}