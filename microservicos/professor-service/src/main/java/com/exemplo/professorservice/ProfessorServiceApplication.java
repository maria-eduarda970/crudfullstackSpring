package com.exemplo.professorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * =====================================================
 * MICROSERVI�O: professor-service
 * =====================================================
 *
 * Este um servio independente responsvel APENAS
 * pelo gerenciamento de professores.
 *
 * Para executar:
 *   mvn spring-boot:run
 *
 * Acesso:
 *   API:        http://localhost:8086/api/professores
 *   H2 Console: http://localhost:8081/h2-console
 *
 * =====================================================
 * DIFEREN�AS em rela��o ao monolito:
 * =====================================================
 *
 * MONOLITO                        | MICROSERVI�O
 * --------------------------------|----------------------------
 * Uma aplica��o, todas entidades  | Uma aplica��o por entidade
 * Um banco compartilhado          | Banco exclusivo (professordb)
 * Porta 8080                      | Porta 8086
 * Comunica internamente (chamada  | Comunica via HTTP REST
 *   de mtodo Java)               |   com outros servios
 *
 * =====================================================
 */
@SpringBootApplication
public class ProfessorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProfessorServiceApplication.class, args);
    }
}
