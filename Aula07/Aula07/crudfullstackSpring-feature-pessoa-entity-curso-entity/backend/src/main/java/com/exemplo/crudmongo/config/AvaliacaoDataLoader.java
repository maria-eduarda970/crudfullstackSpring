package com.exemplo.crudmongo.config;

import com.exemplo.crudmongo.Model.Avaliacao;
import com.exemplo.crudmongo.repository.AvaliacaoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.javafaker.Faker;

import java.util.Locale;

@Configuration
public class AvaliacaoDataLoader {

    @Bean
    CommandLineRunner loadAvaliacaoDatabase(AvaliacaoRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Faker faker = new Faker(new Locale("pt-BR"));

                for (int i = 0; i < 200; i++) {
                    Avaliacao avaliacao = new Avaliacao();
                    avaliacao.setPessoa(faker.number().randomNumber());
                    avaliacao.setDisciplina(faker.number().randomNumber());
                    avaliacao.setNota(faker.number().numberBetween(0, 10));
                    avaliacao.setData(String.valueOf(faker.number().numberBetween(2023, 2027)));
                    avaliacao.setAtivo(faker.bool().bool());
                    repository.save(avaliacao);
                }

                System.out.println(" Banco de avaliaçoes com 200 registros!");
            } else {
                System.out.println(" Banco de avaliações já contém dados, não foi necessário adicionar.");
            }
        };
    }
}
