package com.exemplo.avaliacaoservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidade Avaliacao ? pertence exclusivamente a este microservio.
 *
 * IMPORTANTE: em microservios, NO usamos @ManyToOne com outras entidades
 * de outros servios. Em vez disso, guardamos apenas os IDs (pessoaId, cursoId).
 * A consulta ao nome da pessoa ou curso feita via chamada REST ao
 * respectivo servio, se necessrio.
 */
@Entity
@Table(name = "avaliacao")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pessoaId;
    private Long disciplinaId;
    private double nota;
    private String data;
    private boolean ativo;

    public Avaliacao() {}

    public Avaliacao(Long id, Long pessoaId, Long disciplinaId, double nota, String data, boolean ativo) {
        this.id = id;
        this.pessoaId = pessoaId;
        this.disciplinaId = disciplinaId;
        this.nota = nota;
        this.data = data;
        this.ativo = ativo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPessoaId() { return pessoaId; }
    public void setPessoaId(Long pessoaId) { this.pessoaId = pessoaId; }
    public Long getDisciplinaId() { return disciplinaId; }
    public void setDisciplinaId(Long disciplinaId) { this.disciplinaId = disciplinaId; }
    public double getNota() { return nota; }
    public void setNota(double nota) { this.nota = nota; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
