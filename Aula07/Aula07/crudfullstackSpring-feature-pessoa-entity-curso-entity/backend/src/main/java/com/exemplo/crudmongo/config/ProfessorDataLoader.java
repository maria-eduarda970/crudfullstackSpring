package com.exemplo.crudmongo.config;
// Esse é o "endereço" onde ficam as configurações do sistema

import com.exemplo.crudmongo.Model.Professor;
// Importa a ficha do professor

import com.exemplo.crudmongo.repository.ProfessorRepository;
// Importa quem conversa com o banco (para professores)

import org.springframework.boot.CommandLineRunner;
// Importa algo que executa um código automaticamente quando o sistema inicia

import org.springframework.context.annotation.Bean;
// Diz que esse método vai criar algo que o sistema pode usar

import org.springframework.context.annotation.Configuration;
// Marca essa classe como configuração do sistema

import com.github.javafaker.Faker;
// Importa uma ferramenta que cria dados falsos (nomes, emails, etc.)

import java.util.Locale;
// Importa a configuração de idioma (português)

@Configuration
// Diz que essa classe é de configuração

public class ProfessorDataLoader {

    @Bean
    // Esse método roda automaticamente quando o sistema inicia

    CommandLineRunner loadProfessorDatabase(ProfessorRepository repository) {
        // Recebe o repositório (quem salva no banco)

        return args -> {
            // Esse código roda sozinho ao iniciar o sistema

            if (repository.count() == 0) {
                // Verifica se o banco está vazio

                Faker faker = new Faker(new Locale("pt-BR"));
                // Cria um gerador de dados falsos em português

                for (int i = 0; i < 200; i++) {
                    // Repeat 200 vezes

                    Professor professor = new Professor();
                    // Cria uma nova ficha de professor

                    professor.setNome(faker.name().fullName());
                    // Coloca um nome aleatório

                    professor.setArea(faker.job().field());
                    // Define uma área de atuação aleatória

                    professor.setAtivo(faker.bool().bool());
                    // Define aleatoriamente se está ativo ou não


                    repository.save(professor);
                    // Salva o professor no banco
                }

                System.out.println(" Banco de professores populado com 200 registros!");
                // Mostra mensagem de sucesso
            } else {
                // Se já tiver dados no banco...

                System.out.println(" Banco de professores já contém dados, não foi necessário repopular.");
                // Mostra mensagem dizendo que não precious fazer nada
            }
        };
    }
}