package com.exemplo.pessoaservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.exemplo.pessoaservice.dto.Cursodto;

@Service
public class Cursoclient {

    private final RestTemplate restTemplate;

    @Value("${curso.service.url}")
    private String cursoServiceUrl;

    public Cursoclient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // ✔ MÉTODO CORRETO (usado no service)
    public Cursodto buscarCurso(Long id) {

        try {
            String url = cursoServiceUrl + "/api/cursos/" + id;

            return restTemplate.getForObject(
                    url,
                    Cursodto.class
            );

        } catch (RestClientException e) {

            // ✔ FALLBACK NÍVEL 3
            Cursodto curso = new Cursodto();
            curso.setNome("indisponível");

            return curso;
        }
    }

    // ✔ MÉTODO OPCIONAL (compatibilidade / facilita service)
    public String buscarNomeCurso(Long id) {
        Cursodto curso = buscarCurso(id);

        return (curso != null && curso.getNome() != null)
                ? curso.getNome()
                : "indisponível";
    }
}
