package com.exemplo.avaliacaoservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.exemplo.avaliacaoservice.dto.Disciplinadto;

@Service
public class Disciplinaclient {

    private final RestTemplate restTemplate;

    @Value("${disciplina.service.url}")
    private String disciplinaServiceUrl;

    public Disciplinaclient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

public Disciplinadto buscarDisciplina(Long id) {

    try {

        String url = disciplinaServiceUrl + "/api/disciplinas/" + id;

        return restTemplate.getForObject(
                url,
                Disciplinadto.class
        );

    } catch (RestClientException e) {

        Disciplinadto disciplina = new Disciplinadto();
        disciplina.setNome("indisponível");

        return disciplina;
    }
}
}