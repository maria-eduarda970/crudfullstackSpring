package com.exemplo.avaliacaoservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.exemplo.avaliacaoservice.dto.Pessoadto;

@Service
public class Pessoaclient {

    private final RestTemplate restTemplate;

    @Value("${pessoa.service.url}")
    private String pessoaServiceUrl;

    public Pessoaclient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

public Pessoadto buscarPessoa(Long id) {

    try {

        String url = pessoaServiceUrl + "/api/pessoas/" + id;

        return restTemplate.getForObject(
                url,
                Pessoadto.class
        );

    } catch (RestClientException e) {

        Pessoadto pessoa = new Pessoadto();
        pessoa.setNome("indisponível");

        return pessoa;
    }
}
}
