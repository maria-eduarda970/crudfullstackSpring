package com.exemplo.turmaservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int ano;
    private boolean ativo;

    // 🔗 ligação com professor-service (NÍVEL 2)
    private Long professorId;

    // 🔥 campo enriquecido (NÍVEL 2)
    private String nomeProfessor;

    public Turma() {
    }

    public Turma(Long id, String nome, int ano, boolean ativo,
                 Long professorId, String nomeProfessor) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
        this.ativo = ativo;
        this.professorId = professorId;
        this.nomeProfessor = nomeProfessor;
    }

    // =========================================================
    // GETTERS E SETTERS
    // =========================================================

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

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }
}