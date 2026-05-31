package com.exemplo.cursoservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemplo.cursoservice.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByAtivo(boolean ativo);
    List<Curso> findByNomeContainingIgnoreCase(String nome);
}
