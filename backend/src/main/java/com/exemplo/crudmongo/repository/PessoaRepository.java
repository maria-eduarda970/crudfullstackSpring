package com.exemplo.crudmongo.repository;

import com.exemplo.crudmongo.Model.Pessoa;

//base para pesquisa e consultas
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.mongodb.repository.MongoRepository; remover esse código
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {


    // Buscar por nome contendo um texto (ex: parte do nome)
    List<Pessoa> findByNomeContainingIgnoreCase(String nome);

    // Buscar pessoas com idade exata
    List<Pessoa> findByIdade(Integer idade);

    // Paginação automática
    Page<Pessoa> findAll(Pageable pageable);

}