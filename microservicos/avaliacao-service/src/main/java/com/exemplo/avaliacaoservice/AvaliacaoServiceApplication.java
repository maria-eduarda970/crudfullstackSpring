package com.exemplo.avaliacaoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * =====================================================
 * MICROSERVIÇO: avaliacao-service
 * =====================================================
 *
 * Este é um serviço independente responsável APENAS
 * pelo gerenciamento de avaliações.
 *
 * Para executar:
 *   mvn spring-boot:run
 *
 * Acesso:
 *   API:        http://localhost:8082/api/avaliacoes
 *   H2 Console: http://localhost:8082/h2-console
 *
 * =====================================================
 * MICROSERVIÇOS:
 * =====================================================
 *
 * matricula-service   → 8081
 * avaliacao-service    → 8082
 * curso-service        → 8083
 * disciplina-service   → 8084
 * pessoa-service       → 8085
 * professor-service    → 8086
 * turma-service        → 8087
 *
 * =====================================================
 */
@SpringBootApplication
public class AvaliacaoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AvaliacaoServiceApplication.class, args);
    }
}
