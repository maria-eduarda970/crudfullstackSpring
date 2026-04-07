package com.exemplo.crudmongo.Model;
// Esse é o "endereço" da classe dentro do projeto (onde ela mora)

import jakarta.persistence.Entity;
// Importa algo que diz: "essa classe vai virar uma tabela no banco"

import jakarta.persistence.GeneratedValue;
// Importa algo que cria o ID automaticamente

import jakarta.persistence.GenerationType;
// Define como o ID será criado

import jakarta.persistence.Id;
// Marca qual campo é o identificador único (tipo CPF)

import jakarta.persistence.Table;
// Serve para dar nome à tabela no banco

@Entity
// Aqui estamos dizendo: essa classe é uma tabela no banco de dados

@Table(name = "professor")
// Aqui diz que o nome da tabela será "professor"

public class Professor {
// Aqui criamos o "molde" de um professor (tipo uma ficha com informações)

    @Id
    // Esse campo é o identificador único

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // O ID será criado automaticamente pelo banco

    private Long id;
    // Guarda o número único do professor

    private String nome;
    // Guarda o nome do professor

    private String area;
    // Guarda a área do professor

    private boolean ativo;
    // Guarda se o professor está ativo (true = sim, false = não)

    public Professor() {
    }
    // Esse é o construtor (serve para criar um novo professor vazio)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}