package com.exemplo.pessoaservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.exemplo.pessoaservice.model.Pessoa;
import com.exemplo.pessoaservice.repository.PessoaRepository;

/**
 * Camada de negócio do microserviço de Pessoas.
 * Toda a lógica de negócio fica aqui — o controller só delega.
 */
@Service
public class PessoaService {

    private final PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    /** Lista todas as pessoas */
    public List<Pessoa> listarTodas() {
        return repository.findAll();
    }

    /** Busca uma pessoa pelo ID */
    public Optional<Pessoa> buscarPorId(Long id) {
        return repository.findById(id);
    }

    /** Lista pessoas por nome */
    public List<Pessoa> listarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    /** Lista pessoas por idade */
    public List<Pessoa> listarPorIdade(int idade) {
        return repository.findByIdade(idade);
    }

    /** Lista pessoas por email */
    public List<Pessoa> listarPorEmail(String email) {
        return repository.findByEmail(email);
    }

    /** Lista pessoas por telefone */
    public List<Pessoa> listarPorTelefone(String telefone) {
        return repository.findByTelefone(telefone);
    }

    /** Cria uma nova pessoa */
    public Pessoa salvar(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    /** Atualiza uma pessoa existente */
    public Pessoa atualizar(Long id, Pessoa dados) {
        Pessoa existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada: " + id));

        existente.setNome(dados.getNome());
        existente.setIdade(dados.getIdade());
        existente.setEmail(dados.getEmail());
        existente.setTelefone(dados.getTelefone());
        existente.setAtivo(dados.isAtivo());

        return repository.save(existente);
    }

    /** Desativa uma pessoa (soft delete) */
    public void desativar(Long id) {
        Pessoa existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada: " + id));

        existente.setAtivo(false);

        repository.save(existente);
    }

    /** Remove permanentemente uma pessoa */
    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
