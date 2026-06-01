package com.exemplo.turmaservice.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.exemplo.turmaservice.model.Turma;
import com.exemplo.turmaservice.repository.TurmaRepository;

@Configuration
public class TurmaDataLoader {

    @Bean
    CommandLineRunner carregarDados(TurmaRepository repository) {
        return args -> {

            if (repository.count() > 0) return;

            Turma t1 = new Turma();
            t1.setNome("Turma ADS 2024");
            t1.setAno(2024);
            t1.setAtivo(true);
            t1.setProfessorId(1L);
            t1.setNomeProfessor("Carlos Alberto");

            Turma t2 = new Turma();
            t2.setNome("Turma CC 2024");
            t2.setAno(2024);
            t2.setAtivo(true);
            t2.setProfessorId(2L);
            t2.setNomeProfessor("Fernanda Lima");

            Turma t3 = new Turma();
            t3.setNome("Turma ES 2025");
            t3.setAno(2025);
            t3.setAtivo(true);
            t3.setProfessorId(3L);
            t3.setNomeProfessor("Ricardo Souza");

            Turma t4 = new Turma();
            t4.setNome("Turma Redes 2023");
            t4.setAno(2023);
            t4.setAtivo(false);
            t4.setProfessorId(1L);
            t4.setNomeProfessor("Carlos Alberto");

            repository.save(t1);
            repository.save(t2);
            repository.save(t3);
            repository.save(t4);

            System.out.println("✅ [turma-service] Dados iniciais carregados com professor.");
        };
    }
}
