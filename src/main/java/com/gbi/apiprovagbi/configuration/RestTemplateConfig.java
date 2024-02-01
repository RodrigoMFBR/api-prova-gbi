package com.gbi.apiprovagbi.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Value("${gbi.api.user}")
    private String username;

    @Value("${gbi.api.password}")
    private String password;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // Adicione um interceptor para incluir as credenciais em cada chamada
        restTemplate.getInterceptors().add((ClientHttpRequestInterceptor) (request, body, execution) -> {
            String credentials = username + ":" + password;
            String base64Credentials = new String(java.util.Base64.getEncoder().encode(credentials.getBytes()));
            request.getHeaders().add("Authorization", "Basic " + base64Credentials);
            return execution.execute(request, body);
        });

        return restTemplate;
    }
}
