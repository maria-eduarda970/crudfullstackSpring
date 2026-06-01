package com.exemplo.turmaservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.exemplo.turmaservice.dto.Professordto;

@Service
public class Professorclient {

    private final RestTemplate restTemplate;

    @Value("${professor.service.url}")
    private String professorServiceUrl;

    public Professorclient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Professordto buscarProfessor(Long id) {

        try {
            String url = professorServiceUrl + "/api/professores/" + id;

            return restTemplate.getForObject(url, Professordto.class);

        } catch (RestClientException e) {

            Professordto professor = new Professordto();
            professor.setNome("indisponível");

            return professor;
        }
    }
}
