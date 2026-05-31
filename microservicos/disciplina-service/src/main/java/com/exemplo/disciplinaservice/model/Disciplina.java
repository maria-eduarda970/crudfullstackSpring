package com.exemplo.disciplinaservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidade Disciplina ? pertence exclusivamente a este microservio.
 *
 * IMPORTANTE: em microservios, NO usamos @ManyToOne com outras entidades
 * de outros servios. Em vez disso, guardamos apenas os IDs (pessoaId, cursoId).
 * A consulta ao nome da pessoa ou curso feita via chamada REST ao
 * respectivo servio, se necessrio.
 */
@Entity
@Table(name = "disciplina")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private boolean ativo;

    public Disciplina() {}

    public Disciplina(Long id, String nome, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.ativo = ativo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}