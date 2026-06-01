package com.exemplo.professorservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.exemplo.professorservice.dto.Turmadto;

@Service
public class Turmaclient {

    private final RestTemplate restTemplate;

    @Value("${turma.service.url}")
    private String turmaServiceUrl;

    public Turmaclient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Turmadto buscarTurma(Long id) {

        try {
            String url = turmaServiceUrl + "/api/turmas/" + id;

            return restTemplate.getForObject(url, Turmadto.class);

        } catch (RestClientException e) {

            Turmadto turma = new Turmadto();
            turma.setNome("indisponível");

            return turma;
        }
    }
}
