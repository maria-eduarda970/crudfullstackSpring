package com.exemplo.disciplinaservice.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.exemplo.disciplinaservice.model.Disciplina;
import com.exemplo.disciplinaservice.repository.DisciplinaRepository;

/**
 * Carrega dados iniciais no banco H2 ao subir o microserviço.
 * Útil para testes e demonstrações.
 */
@Configuration
public class DisciplinaDataLoader {

    @Bean
    CommandLineRunner carregarDados(DisciplinaRepository repository) {
        return args -> {

            Disciplina d1 = new Disciplina();
            d1.setNome("Banco de Dados");
            d1.setAtivo(true);

            Disciplina d2 = new Disciplina();
            d2.setNome("Programação Orientada a Objetos");
            d2.setAtivo(true);

            Disciplina d3 = new Disciplina();
            d3.setNome("Redes de Computadores");
            d3.setAtivo(true);

            Disciplina d4 = new Disciplina();
            d4.setNome("Engenharia de Software");
            d4.setAtivo(false);

            repository.save(d1);
            repository.save(d2);
            repository.save(d3);
            repository.save(d4);

            System.out.println("✅ [disciplina-service] Dados iniciais carregados.");
        };
    }
}
