package com.gbi.apiprovagbi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GbiService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${correios.api.url}")
    private String correiosApiUrl;

    // Método para consultar CEP
    public String getAddressByCep(String cep) {
        String apiUrl = correiosApiUrl + cep + "/json";
        return restTemplate.getForObject(apiUrl, String.class);
    }
}
