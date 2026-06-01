package com.exemplo.professorservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int idade;
    private String email;
    private String area;
    private boolean ativo;

    // 🔗 IDs de outros microserviços (NÍVEL 2)
    private Long cursoId;
    private Long turmaId;

    // 🔥 Campos enriquecidos via chamada HTTP (NÍVEL 2)
    private String nomeCurso;
    private String nomeTurma;

    public Professor() {
    }

    public Professor(Long id, String nome, int idade, String email,
                     String area, boolean ativo,
                     Long cursoId, Long turmaId,
                     String nomeCurso, String nomeTurma) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.area = area;
        this.ativo = ativo;
        this.cursoId = cursoId;
        this.turmaId = turmaId;
        this.nomeCurso = nomeCurso;
        this.nomeTurma = nomeTurma;
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public Long getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(Long turmaId) {
        this.turmaId = turmaId;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }
}
