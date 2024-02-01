package com.gbi.apiprovagbi.test;

import com.gbi.apiprovagbi.service.GbiService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GbiServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GbiService gbiService;

    @Value("${correios.api.url}")
    private String correiosApiUrl;

    @Test
    void buscarEnderecoPorCepInexistenteDeveRetornarVazio() throws IOException {
        // Configuração do Mock para uma chamada mal-sucedida
        when(restTemplate.getForObject(any(String.class), any(Class.class))).thenReturn(null);

        // Execução do método a ser testado
        String resultado = gbiService.getAddressByCep("11111111");

        // Verificação do resultado
        assertEquals("CEP N ENCONTRADO", resultado);

    }

    @Test
    void buscarEnderecoPorCepExistente() throws IOException {
        // Configuração do Mock para uma chamada bem-sucedida
        when(restTemplate.getForObject(any(String.class), any(Class.class))).thenReturn("CEP OK");

        // Execução do método a ser testado
        String resultado = gbiService.getAddressByCep("06142150");

        // Verificação do resultado
        assertEquals("CEP OK", resultado);
    }
}
