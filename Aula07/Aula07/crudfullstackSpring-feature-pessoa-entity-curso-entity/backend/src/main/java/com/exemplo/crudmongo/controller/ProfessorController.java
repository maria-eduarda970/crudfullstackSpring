package com.exemplo.crudmongo.controller;
// Esse é o "endereço" do Controller dentro do projeto

import com.exemplo.crudmongo.Model.Professor;
// Importa a ficha do professor

import com.exemplo.crudmongo.service.ProfessorService;
// Importa o "cérebro" que faz as regras

import org.springframework.web.bind.annotation.*;
// Importa várias ferramentas para trabalhar com requisições da internet

import java.util.List;
// Importa lista (para guardar vários professores)

@RestController
// Diz que essa classe vai receber e responder requisições (tipo um atendente)

@RequestMapping("/api/professor")
// Define o caminho principal da API (tipo o endereço no navegador)

@CrossOrigin(origins = "*")
// Permite que qualquer sistema (ex: frontend) acesse essa API

public class ProfessorController {

    private final ProfessorService service;
    // Aqui guardamos o serviço (o cérebro)

    public ProfessorController(ProfessorService service) {
        // Quando cria o controller, ele já recebe o service pronto
        this.service = service;
    }

    @GetMapping
    // Quando alguém faz uma requisição GET (buscar dados)

    public List<Professor> listar() {
        // Método para listar todos os professores
        return service.listarTodas();
        // Pede pro service buscar tudo
    }

    @PostMapping
    // Quando alguém quer criar um novo professor

    public Professor criar(@RequestBody Professor professor) {
        // Recebe os dados do professor no corpo da requisição
        return service.salvar(professor);
        // Manda salvar
    }

    @PutMapping("/{id}")
    // Quando alguém quer atualizar um professor específico

    public Professor atualizar(@PathVariable Long id, @RequestBody Professor professor) {
        // Pega o ID da URL e os dados novos
        return service.atualizar(id, professor);
        // Manda atualizar
    }

    @DeleteMapping("/{id}")
    // Quando alguém quer excluir um professor

    public void excluir(@PathVariable Long id) {
        // Pega o ID da URL
        service.excluir(id);
        // Manda excluir
    }
}