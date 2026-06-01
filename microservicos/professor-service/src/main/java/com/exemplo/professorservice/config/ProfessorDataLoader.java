package com.exemplo.professorservice.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.exemplo.professorservice.model.Professor;
import com.exemplo.professorservice.repository.ProfessorRepository;

@Configuration
public class ProfessorDataLoader {

    @Bean
    CommandLineRunner carregarDados(ProfessorRepository repository) {
        return args -> {

            if (repository.count() > 0) return;

            Professor p1 = new Professor();
            p1.setNome("Carlos Alberto");
            p1.setIdade(45);
            p1.setEmail("carlos.alberto@gmail.com");
            p1.setArea("Banco de Dados");
            p1.setAtivo(true);
            p1.setCursoId(1L);
            p1.setTurmaId(1L);
            p1.setNomeCurso("Análise e Desenvolvimento de Sistemas");
            p1.setNomeTurma("ADS - Noite 2024");

            Professor p2 = new Professor();
            p2.setNome("Fernanda Lima");
            p2.setIdade(38);
            p2.setEmail("fernanda.lima@gmail.com");
            p2.setArea("Programação Orientada a Objetos");
            p2.setAtivo(true);
            p2.setCursoId(2L);
            p2.setTurmaId(2L);
            p2.setNomeCurso("Engenharia de Software");
            p2.setNomeTurma("ES - Manhã 2024");

            Professor p3 = new Professor();
            p3.setNome("Ricardo Souza");
            p3.setIdade(50);
            p3.setEmail("ricardo.souza@gmail.com");
            p3.setArea("Redes de Computadores");
            p3.setAtivo(true);
            p3.setCursoId(3L);
            p3.setTurmaId(3L);
            p3.setNomeCurso("Redes de Computadores");
            p3.setNomeTurma("Redes - Noite 2024");

            Professor p4 = new Professor();
            p4.setNome("Juliana Martins");
            p4.setIdade(41);
            p4.setEmail("juliana.martins@gmail.com");
            p4.setArea("Engenharia de Software");
            p4.setAtivo(false);
            p4.setCursoId(1L);
            p4.setTurmaId(2L);
            p4.setNomeCurso("Análise e Desenvolvimento de Sistemas");
            p4.setNomeTurma("ADS - Manhã 2024");

            repository.save(p1);
            repository.save(p2);
            repository.save(p3);
            repository.save(p4);

            System.out.println("✅ [professor-service] Dados carregados com curso e turma (nome incluso).");
        };
    }
}
