package com.exemplo.cursoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * =====================================================
 * MICROSERVIÇO: curso-service
 * =====================================================
 *
 * Serviço responsável pelo gerenciamento de cursos.
 *
 * Para executar:
 *   mvn spring-boot:run
 *
 * Acesso:
 *   API:        http://localhost:8083/api/cursos
 *   H2 Console: http://localhost:8083/h2-console
 *
 * =====================================================
 * MICROSERVIÇOS:
 * =====================================================
 *
 * matricula-service   → 8081
 * avaliacao-service    → 8082
 * curso-service        → 8083
 * disciplina-service   → 8084
 * pessoa-service      → 8085
 * professor-service    → 8086
 * turma-service       → 8087
 *
 * =====================================================
 */
@SpringBootApplication
public class CursoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CursoServiceApplication.class, args);
    }
}
