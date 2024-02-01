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

        if (cep.length() != 8) {
            return "CEP inválido";
        } else {
            String retornoServico = restTemplate.getForObject(apiUrl, String.class);

            if (retornoServico.contains("cep")) {
                return retornoServico;
            } else {
                return "CEP não encontrado";
            }
        }

    }
}
