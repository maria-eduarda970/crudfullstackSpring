package com.exemplo.pessoaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * =====================================================
 * MICROSERVI�O: pessoa-service
 * =====================================================
 *
 * Este um servio independente responsvel APENAS
 * pelo gerenciamento de pessoas.
 *
 * Para executar:
 *   mvn spring-boot:run
 *
 * Acesso:
 *   API:        http://localhost:8081/api/pessoas
 *   H2 Console: http://localhost:8081/h2-console
 *
 * =====================================================
 * DIFERENAS em relao ao monolito:
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
public class PessoaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PessoaServiceApplication.class, args);
    }
}
