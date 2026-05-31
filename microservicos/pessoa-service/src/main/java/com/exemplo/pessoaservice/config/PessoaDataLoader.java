package com.exemplo.pessoaservice.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.exemplo.pessoaservice.model.Pessoa;
import com.exemplo.pessoaservice.repository.PessoaRepository;

/**
 * Carrega dados iniciais no banco H2 ao subir o microserviço.
 * Útil para testes e demonstrações.
 */
@Configuration
public class PessoaDataLoader {

    @Bean
    CommandLineRunner carregarDados(PessoaRepository repository) {
        return args -> {

            Pessoa p1 = new Pessoa();
            p1.setNome("Maria Eduarda");
            p1.setIdade(20);
            p1.setEmail("maria@gmail.com");
            p1.setTelefone("(61)99999-1111");
            p1.setAtivo(true);

            Pessoa p2 = new Pessoa();
            p2.setNome("João Pedro");
            p2.setIdade(22);
            p2.setEmail("joao@gmail.com");
            p2.setTelefone("(61)99999-2222");
            p2.setAtivo(true);

            Pessoa p3 = new Pessoa();
            p3.setNome("Ana Clara");
            p3.setIdade(19);
            p3.setEmail("ana@gmail.com");
            p3.setTelefone("(61)99999-3333");
            p3.setAtivo(true);

            Pessoa p4 = new Pessoa();
            p4.setNome("Carlos Henrique");
            p4.setIdade(25);
            p4.setEmail("carlos@gmail.com");
            p4.setTelefone("(61)99999-4444");
            p4.setAtivo(false);

            repository.save(p1);
            repository.save(p2);
            repository.save(p3);
            repository.save(p4);

            System.out.println("✅ [pessoa-service] Dados iniciais carregados.");
        };
    }
}