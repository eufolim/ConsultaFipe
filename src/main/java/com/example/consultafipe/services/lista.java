package com.example.consultafipe.services;

/* import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set; */

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class lista {
    static RestClient cliente = RestClient.create("https://parallelum.com.br");
    

    /* @GetMapping("/marcas")     */
    /* @ResponseBody */
    public static String getMarcas(String marca) {
        JsonNode marcas = cliente
            .get()
            .uri("/fipe/api/v1/carros/marcas")
            .retrieve()
            .body(JsonNode.class);
        String idMarca = null;
        for (int i = 0; i < marcas.size(); i++) {
            JsonNode temp = (JsonNode) marcas.get(i);
            System.out.println(temp.path("nome").asText());
            if (temp.path("nome").asText().regionMatches(true, 0, marca, 0, marca.length())){
                idMarca = temp.path("codigo").asText();
                System.out.println(temp.path("nome").asText());
                break;
            }
            /* if (temp.path("nome").asText().regionMatches(true, temp.path("nome").asText().length(), marca, temp.path("nome").asText().length(), marca.length())){
                idMarca = temp.path("codigo").asText();
                System.out.println(temp.path("nome").asText());
                break;
            } */
        }
        
        /* Set<Map.Entry<String,String>> entrySet = marcas.entrySet();
        Iterator<Map.Entry<String,String>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getValue().equals(marca)) {
                idMarca = iterator.next().getKey();
                System.out.println("marcas done");
                break;
            }
            iterator.next();
        }   
            */
        System.out.println(idMarca);
        return idMarca;
    }

    /* @GetMapping("/modelos") */
    @ResponseBody
    public static String getModelos(String idMarca, String modelo) {
        JsonNode modelos = cliente
            .get()
            .uri("/fipe/api/v1/carros/marcas/"+idMarca+"/modelos")
            .retrieve()
            .body(JsonNode.class);
        String idModelo = null;
        for (int i = 0; i < modelos.path("modelos").size(); i++) {
            JsonNode temp = (JsonNode) modelos.path("modelos").get(i);
            if (temp.path("nome").asText().regionMatches(true, 0, modelo, 0, modelo.length())){
                idModelo = temp.path("codigo").asText();
                System.out.println(temp.path("nome").asText());
                break;
            }
        }
        return idModelo;
    }
    
    /* @GetMapping("/anos") */
    @ResponseBody
    public static String getAnos(String idMarca, String idModelo, String ano) {
        JsonNode anos = cliente
            .get()
            .uri("/fipe/api/v1/carros/marcas/"+idMarca+"/modelos/"+idModelo+"/anos")
            .retrieve()
            .body( JsonNode.class);
        String idAno = null;
        for (int i = 0; i < anos.size(); i++) {
            JsonNode temp = (JsonNode) anos.get(i);
            if (temp.path("nome").asText().regionMatches(true, 0, ano, 0, ano.length())){
                idAno = temp.path("codigo").asText();
                System.out.println(temp.path("nome").asText());
                break;
            }
        }
        return idAno;
    }   
}


