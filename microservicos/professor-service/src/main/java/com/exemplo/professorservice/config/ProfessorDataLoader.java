package com.exemplo.professorservice.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.exemplo.professorservice.model.Professor;
import com.exemplo.professorservice.repository.ProfessorRepository;

/**
 * Carrega dados iniciais no banco H2 ao subir o microserviço.
 * Útil para testes e demonstrações.
 */
@Configuration
public class ProfessorDataLoader {

    @Bean
    CommandLineRunner carregarDados(ProfessorRepository repository) {
        return args -> {

            Professor p1 = new Professor();
            p1.setNome("Carlos Alberto");
            p1.setIdade(45);
            p1.setEmail("carlos.alberto@gmail.com");
            p1.setArea("Banco de Dados");
            p1.setAtivo(true);

            Professor p2 = new Professor();
            p2.setNome("Fernanda Lima");
            p2.setIdade(38);
            p2.setEmail("fernanda.lima@gmail.com");
            p2.setArea("Programação Orientada a Objetos");
            p2.setAtivo(true);

            Professor p3 = new Professor();
            p3.setNome("Ricardo Souza");
            p3.setIdade(50);
            p3.setEmail("ricardo.souza@gmail.com");
            p3.setArea("Redes de Computadores");
            p3.setAtivo(true);

            Professor p4 = new Professor();
            p4.setNome("Juliana Martins");
            p4.setIdade(41);
            p4.setEmail("juliana.martins@gmail.com");
            p4.setArea("Engenharia de Software");
            p4.setAtivo(false);

            repository.save(p1);
            repository.save(p2);
            repository.save(p3);
            repository.save(p4);

            System.out.println("✅ [professor-service] Dados iniciais carregados.");
        };
    }
}
