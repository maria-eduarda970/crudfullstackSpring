package com.exemplo.crudmongo.config;

import com.exemplo.crudmongo.Model.Matricula;

import com.exemplo.crudmongo.repository.MatriculaRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.javafaker.Faker;

import java.util.Locale;

@Configuration
public class MatriculaDataLoader {

    @Bean
    CommandLineRunner loadMatriculaDatabase(MatriculaRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Faker faker = new Faker(new Locale("pt-BR"));

                for (int i = 0; i < 200; i++) {
                   Matricula Matricula = new Matricula();
                    Matricula.setNome(faker.name().fullName());
                    Matricula.setIdade(faker.number().numberBetween(18, 80));
                    Matricula.setEmail(faker.internet().emailAddress());
                    Matricula.setAtivo(faker.bool().bool());
                    repository.save(Matricula);
                }

                System.out.println("✅ Banco de matrículas populado com 200 registros!");
            } else {
                System.out.println("ℹ️ Banco de matrículas já contém dados, não foi necessário repopular.");
            }
        };
    }
}
