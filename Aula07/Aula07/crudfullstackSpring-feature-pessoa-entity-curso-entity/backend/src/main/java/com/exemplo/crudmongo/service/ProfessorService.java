package com.exemplo.crudmongo.service;
// Esse é o "endereço" da classe dentro do projeto

import java.util.List;
// Importa uma lista (tipo uma caixa com vários professores dentro)

import org.springframework.stereotype.Service;
// Diz que essa classe é um "serviço" (tipo o cérebro que decide o que fazer)

import org.springframework.web.bind.annotation.PathVariable;
// Serve para pegar o ID que vem da URL (tipo /professor/1)

import com.exemplo.crudmongo.Model.Professor;
// Importa a ficha do professor

import com.exemplo.crudmongo.repository.ProfessorRepository;
// Importa o "funcionário" que conversa com o banco

@Service
// Marca essa classe como responsável pela lógica (as decisões)

public class ProfessorService {

    private final ProfessorRepository repository;
    // Aqui guardamos o repositório (quem mexe no banco)

    public ProfessorService(ProfessorRepository repository) {
        // Quando o sistema cria esse serviço, ele já entrega o repositório pronto
        this.repository = repository;
    }

    public List<Professor> listarTodas() {
        // Esse método serve para listar todos os professores
        return repository.findAll();
        // Pede pro repositório buscar tudo no banco
    }

    public Professor salvar(Professor professor) {
        // Esse método serve para salvar um professor novo
        return repository.save(professor);
        // Manda o repositório guardar no banco
    }

    public Professor atualizar(@PathVariable Long id, Professor novoProfessor) {
        // Esse método serve para atualizar um professor existente

        return repository.findById(id).map(p -> {
            // Primeiro ele procura o professor pelo ID
            p.setId(id);
            p.setNome(novoProfessor.getNome());
            // Atualiza o nome

            p.setArea(novoProfessor.getArea());
            // Atualiza a area

            p.setAtivo(novoProfessor.isAtivo());
            // Atualiza se está ativo ou não

            return repository.save(p);
            // Salva as mudanças no banco

        }).orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        // Se não achar o professor, dá erro
    }

    public void excluir(Long id) {
        // Esse método serve para apagar um professor
        repository.deleteById(id);
        // Pede pro repositório deletar pelo ID
    }
}