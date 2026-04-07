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
                    Matricula matricula = new Matricula();
                    matricula.setId(faker.number().randomNumber());
                    matricula.setPessoa(faker.number().randomNumber());
                    matricula.setCurso(faker.number().randomNumber());
                    matricula.setData(String.valueOf(faker.number().numberBetween(2023, 2027)));
                    matricula.setAtivo(faker.bool().bool());
                    repository.save(matricula);
                }

                System.out.println(" Banco de matricula com 200 registros!");
            } else {
                System.out.println(" Banco de matricula já contém dados, não foi necessário atualizar.");
            }
        };
    }
}
