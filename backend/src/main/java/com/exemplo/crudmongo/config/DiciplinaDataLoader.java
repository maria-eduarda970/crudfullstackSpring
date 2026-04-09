package com.exemplo.crudmongo.config;

import com.exemplo.crudmongo.Model.Diciplina;

import com.exemplo.crudmongo.repository.DiciplinaRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.javafaker.Faker;

import java.util.Locale;

@Configuration
public class DiciplinaDataLoader {

    @Bean
    CommandLineRunner loadDiciplinaDatabase(DiciplinaRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Faker faker = new Faker(new Locale("pt-BR"));

                for (int i = 0; i < 200; i++) {
                    Diciplina Diciplina = new Diciplina();
                    Diciplina.setNome(faker.name().fullName());
                    Diciplina.setIdade(faker.number().numberBetween(18, 80));
                    Diciplina.setEmail(faker.internet().emailAddress());
                    Diciplina.setAtivo(faker.bool().bool());
                    repository.save(Diciplina);
                }

                System.out.println("✅ Banco de diciplinas populado com 200 registros!");
            } else {
                System.out.println("ℹ️ Banco de diciplinas já contém dados, não foi necessário repopular.");
            }
        };
    }
}
