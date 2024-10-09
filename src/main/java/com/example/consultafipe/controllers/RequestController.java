package com.example.consultafipe.controllers;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import com.example.consultafipe.services.lista;

@RestController
@RequestMapping("/fipe")
public class RequestController {
    RestClient cliente = RestClient.create("https://parallelum.com.br");

    @GetMapping("/carro")
    @ResponseBody
    public HashMap<String,String> getMethodName(@RequestParam String marca,@RequestParam String modelo,@RequestParam String ano) {
        String idMarca = lista.getMarcas(marca);
        String idModelo = lista.getModelos(idMarca,modelo);
        String idAno = lista.getAnos(idMarca,idModelo,ano);
        HashMap<String,String> carro = cliente
            .get()
            .uri("/fipe/api/v1/carros/marcas/"+idMarca+"/modelos/"+idModelo+"/anos/"+idAno)
            .retrieve()
            .body(HashMap.class);
        return carro;
    }
    
}
