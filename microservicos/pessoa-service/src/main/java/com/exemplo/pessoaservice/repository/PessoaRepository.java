package com.exemplo.pessoaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemplo.pessoaservice.model.Pessoa;

/**
 * Repositório JPA do microserviço de Pessoas.
 * Herda todos os métodos CRUD de JpaRepository.
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    // Buscar pessoas por nome
    List<Pessoa> findByNome(String nome);

    // Buscar pessoas por idade
    List<Pessoa> findByIdade(int idade);

    // Buscar pessoas por email
    List<Pessoa> findByEmail(String email);

    // Buscar pessoas por telefone
    List<Pessoa> findByTelefone(String telefone);

    // Buscar pessoas ativas
    List<Pessoa> findByAtivo(boolean ativo);
}