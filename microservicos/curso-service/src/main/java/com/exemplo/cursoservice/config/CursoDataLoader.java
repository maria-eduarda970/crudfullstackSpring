package com.exemplo.cursoservice.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.exemplo.cursoservice.model.Curso;
import com.exemplo.cursoservice.repository.CursoRepository;

@Configuration
public class CursoDataLoader {

    @Bean
    CommandLineRunner carregarDados(CursoRepository repository) {
        return args -> {

            System.out.println("🔥 DATA LOADER FUNCIONOU");

            Curso c1 = new Curso();
            c1.setNome("Análise e Desenvolvimento de Sistemas");
            c1.setCargaHoraria(2400);
            c1.setProfessorId(1L);
            c1.setAtivo(true);

            Curso c2 = new Curso();
            c2.setNome("Ciência da Computação");
            c2.setCargaHoraria(3200);
            c2.setProfessorId(2L);
            c2.setAtivo(true);

            Curso c3 = new Curso();
            c3.setNome("Engenharia de Software");
            c3.setCargaHoraria(3000);
            c3.setProfessorId(3L);
            c3.setAtivo(true);

            Curso c4 = new Curso();
            c4.setNome("Sistemas de Informação");
            c4.setCargaHoraria(2800);
            c4.setProfessorId(1L);
            c4.setAtivo(false);

            repository.save(c1);
            repository.save(c2);
            repository.save(c3);
            repository.save(c4);

            System.out.println("✅ Cursos carregados com sucesso!");
        };
    }
}
