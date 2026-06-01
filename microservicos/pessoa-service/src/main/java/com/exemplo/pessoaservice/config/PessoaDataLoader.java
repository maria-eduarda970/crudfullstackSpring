package com.exemplo.pessoaservice.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.exemplo.pessoaservice.model.Pessoa;
import com.exemplo.pessoaservice.repository.PessoaRepository;

@Configuration
public class PessoaDataLoader {

    @Bean
    CommandLineRunner carregarDados(PessoaRepository repository) {
        return args -> {

            if (repository.count() > 0) return;

            Pessoa p1 = new Pessoa();
            p1.setNome("Maria Eduarda");
            p1.setIdade(20);
            p1.setEmail("maria@gmail.com");
            p1.setTelefone("(61) 99999-1111");
            p1.setAtivo(true);
            p1.setCursoId(1L);
            p1.setNomeCurso("Engenharia"); // ✔ preenchido

            Pessoa p2 = new Pessoa();
            p2.setNome("João Pedro");
            p2.setIdade(22);
            p2.setEmail("joao@gmail.com");
            p2.setTelefone("(61) 99999-2222");
            p2.setAtivo(true);
            p2.setCursoId(2L);
            p2.setNomeCurso("Direito"); // ✔ preenchido

            Pessoa p3 = new Pessoa();
            p3.setNome("Ana Clara");
            p3.setIdade(19);
            p3.setEmail("ana@gmail.com");
            p3.setTelefone("(61) 99999-3333");
            p3.setAtivo(true);
            p3.setCursoId(1L);
            p3.setNomeCurso("Engenharia");

            Pessoa p4 = new Pessoa();
            p4.setNome("Carlos Henrique");
            p4.setIdade(25);
            p4.setEmail("carlos@gmail.com");
            p4.setTelefone("(61) 99999-4444");
            p4.setAtivo(false);
            p4.setCursoId(3L);
            p4.setNomeCurso("Medicina");

            Pessoa p5 = new Pessoa();
            p5.setNome("Fernanda Souza");
            p5.setIdade(28);
            p5.setEmail("fernanda@gmail.com");
            p5.setTelefone("(61) 99999-5555");
            p5.setAtivo(true);
            p5.setCursoId(2L);
            p5.setNomeCurso("Direito");

            repository.save(p1);
            repository.save(p2);
            repository.save(p3);
            repository.save(p4);
            repository.save(p5);

            System.out.println("✅ Pessoas carregadas com nomeCurso inicial");
        };
    }
}