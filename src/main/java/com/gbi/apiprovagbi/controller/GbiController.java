package com.gbi.apiprovagbi.controller;

import com.gbi.apiprovagbi.service.GbiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gbiProva/cep")
public class GbiController {

    @Autowired
    private GbiService gbiService;

    @GetMapping("/{cep}")
    public String consultarCep(@PathVariable String cep) {
        return gbiService.getAddressByCep(cep);
    }

    @RequestMapping("/teste")
    public String home(){
        return "Servidor OK";
    }
}
