package com.exemplo.matriculaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * =====================================================
 * MICROSERVI�O: matricula-service
 * =====================================================
 *
 * Este � um servi�o independente respons�vel APENAS
 * pelo gerenciamento de matr�culas.
 *
 * Para executar:
 *   mvn spring-boot:run
 *
 * Acesso:
 *   API:        http://localhost:8081/api/matriculas
 *   H2 Console: http://localhost:8081/h2-console
 *
 * =====================================================
 * DIFEREN�AS em rela��o ao monolito:
 * =====================================================
 *
 * MONOLITO                        | MICROSERVI�O
 * --------------------------------|----------------------------
 * Uma aplica��o, todas entidades  | Uma aplica��o por entidade
 * Um banco compartilhado          | Banco exclusivo (matriculadb)
 * Porta 8080                      | Porta 8081
 * Comunica internamente (chamada  | Comunica via HTTP REST
 *   de m�todo Java)               |   com outros servi�os
 *
 * =====================================================
 */
@SpringBootApplication
public class MatriculaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MatriculaServiceApplication.class, args);
    }
}
