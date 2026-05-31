package com.exemplo.turmaservice.config;

import com.exemplo.turmaservice.model.Turma;
import com.exemplo.turmaservice.repository.TurmaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Carrega dados iniciais no banco H2 ao subir o microserviço.
 * Útil para testes e demonstrações.
 */
@Configuration
public class TurmaDataLoader {

    @Bean
    CommandLineRunner carregarDados(TurmaRepository repository) {
        return args -> {

            Turma t1 = new Turma();
            t1.setNome("Turma ADS 2024");
            t1.setAno(2024);
            t1.setAtivo(true);

            Turma t2 = new Turma();
            t2.setNome("Turma CC 2024");
            t2.setAno(2024);
            t2.setAtivo(true);

            Turma t3 = new Turma();
            t3.setNome("Turma ES 2025");
            t3.setAno(2025);
            t3.setAtivo(true);

            Turma t4 = new Turma();
            t4.setNome("Turma Redes 2023");
            t4.setAno(2023);
            t4.setAtivo(false);

            repository.save(t1);
            repository.save(t2);
            repository.save(t3);
            repository.save(t4);

            System.out.println("✅ [turma-service] Dados iniciais carregados.");
        };
    }
}
