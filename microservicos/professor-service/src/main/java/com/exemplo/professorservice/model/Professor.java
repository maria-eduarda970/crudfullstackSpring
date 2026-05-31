package com.exemplo.professorservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidade Professor ? pertence exclusivamente a este microservio.
 *
 * IMPORTANTE: em microservi�os, N�O usamos @ManyToOne com outras entidades
 * de outros servi�os. Em vez disso, guardamos apenas os IDs (pessoaId, cursoId).
 * A consulta ao nome da pessoa ou curso � feita via chamada REST ao
 * respectivo servi�o, se necess�rio.
 */
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

    public Professor() {}

    public Professor(Long id, String nome, int idade, String email, String area, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.area = area;
        this.ativo = ativo;
    }

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

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
