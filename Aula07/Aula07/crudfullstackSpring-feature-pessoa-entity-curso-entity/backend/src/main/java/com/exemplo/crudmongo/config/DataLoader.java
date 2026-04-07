package com.exemplo.crudmongo.config;

import com.exemplo.crudmongo.Model.Curso;
import com.exemplo.crudmongo.repository.CursoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.javafaker.Faker;


import java.util.Locale;
@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadDatabase(CursoRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Faker faker = new Faker(new Locale("pt-BR"));

                for (int i = 0; i < 200; i++) {
                    Curso curso = new Curso();
                    curso.setNome(faker.educator().course());
                    repository.save(curso);
                }

                System.out.println(" Banco de cursos com 200 registros!");
            } else {
                System.out.println(" Banco de cursos já contém dados, não foi necessário atualizar.");
            }
        };
    }
}
