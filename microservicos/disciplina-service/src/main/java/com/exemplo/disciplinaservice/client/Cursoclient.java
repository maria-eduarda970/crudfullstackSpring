package com.exemplo.disciplinaservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.exemplo.disciplinaservice.dto.Cursodto;

@Service
public class Cursoclient {

    private final RestTemplate restTemplate;

    @Value("${curso.service.url}")
    private String cursoServiceUrl;

    public Cursoclient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Cursodto buscarCurso(Long id) {

        try {

            String url = cursoServiceUrl + "/api/cursos/" + id;

            return restTemplate.getForObject(
                    url,
                    Cursodto.class
            );

        } catch (RestClientException e) {

            Cursodto curso = new Cursodto();
            curso.setNome("indisponível");

            return curso;
        }
    }
}
