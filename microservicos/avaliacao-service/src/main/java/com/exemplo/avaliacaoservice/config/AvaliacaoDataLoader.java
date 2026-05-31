package com.exemplo.avaliacaoservice.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.exemplo.avaliacaoservice.model.Avaliacao;
import com.exemplo.avaliacaoservice.repository.AvaliacaoRepository;

/**
 * Carrega dados iniciais no banco H2 ao subir o microserviço.
 * Útil para testes e demonstrações.
 */
@Configuration
public class AvaliacaoDataLoader {

    @Bean
    CommandLineRunner carregarDados(AvaliacaoRepository repository) {
        return args -> {
repository.save(new Avaliacao(1L, 1L, 1L, 8.5, "2024-03-10", true));
repository.save(new Avaliacao(2L, 2L, 1L, 7.0, "2024-03-12", true));
repository.save(new Avaliacao(3L, 1L, 2L, 9.0, "2024-03-15", true));
repository.save(new Avaliacao(4L, 3L, 2L, 5.5, "2024-03-18", false));

            System.out.println("✅ [avaliacao-service] Dados iniciais carregados.");
        };
    }
}
