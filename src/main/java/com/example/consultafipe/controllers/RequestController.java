package com.example.consultafipe.controllers;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import com.example.consultafipe.services.lista;

@RestController
@RequestMapping("")
public class RequestController {
    RestClient cliente = RestClient.create("https://parallelum.com.br");

    @GetMapping("/marca")
    @ResponseBody
    public Object test() {
        Object resp = lista.getMarcas("fiat");
        return resp;
    }

    @GetMapping("/modelo")
    @ResponseBody
    public Object test2() {
        Object resp = lista.getModelos(lista.getMarcas("fiat"), "uno");
        return resp;
    }

    @GetMapping("/fipe")
    @ResponseBody
    public Object getMethodName(@RequestParam String marca,@RequestParam String modelo,@RequestParam String ano) {
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
    
    @GetMapping("/sobre")
    @ResponseBody
    public Object sobre() {
        HashMap<String,String> sobre = new HashMap<>();
        sobre.put("estudante", "Luiz Antônio Frey");
        sobre.put("projeto", "test");
        return ResponseEntity.ok().body(sobre);
    }
}
