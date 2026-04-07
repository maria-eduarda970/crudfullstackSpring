package com.exemplo.crudmongo.repository;
// Esse é o "endereço" onde o repositório fica dentro do projeto

import com.exemplo.crudmongo.Model.Professor;
// Aqui estamos pegando a classe Professor (a ficha do professor)

import org.springframework.data.jpa.repository.JpaRepository;
// Importa algo pronto que já sabe salvar, buscar, editar e deletar no banco

import org.springframework.stereotype.Repository;
// Diz que essa classe é responsável por conversar com o banco de dados

@Repository
// Marca que isso aqui é um "repositório" (quem cuida do banco)

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
// Aqui criamos o repositório do Professor
// Ele já vem com várias funções prontas (tipo salvar, buscar, deletar)

// Professor → é o tipo de dado (a ficha do professor)
// Long → é o tipo do ID (o identificador)

}