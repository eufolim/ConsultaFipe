package com.example.consultafipe.services;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClient;

@Service
public class lista {
    static RestClient cliente = RestClient.create("https://parallelum.com.br");
    

    /* @GetMapping("/marcas")     */
    @ResponseBody
    public static String getMarcas(String marca) {
        LinkedHashMap<String,String> marcas = cliente
            .get()
            .uri("/fipe/api/v1/carros/marcas")
            .retrieve()
            .body(LinkedHashMap.class);
        String idMarca = null;
        Set<Map.Entry<String,String>> entrySet = marcas.entrySet();
        Iterator<Map.Entry<String,String>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getValue().equals(marca)) {
                idMarca = iterator.next().getKey();
                System.out.println("marcas done");
                break;
            }
            iterator.next();
        }   
        return idMarca;
    }

    /* @GetMapping("/modelos") */
    @ResponseBody
    public static String getModelos(String idMarca, String modelo) {
        LinkedHashMap<String,String> modelos = cliente
            .get()
            .uri("/fipe/api/v1/carros/marcas/"+idMarca+"/modelos")
            .retrieve()
            .body(LinkedHashMap.class);
        String idModelo = null;
        Set<Map.Entry<String,String>> entrySet = modelos.entrySet();
        Iterator<Map.Entry<String,String>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getValue().equals(modelo)) {
                idModelo = iterator.next().getKey();
                System.out.println("modelos done");
                break;
            }
            iterator.next();
        }   
        return idModelo;
    }
    
    /* @GetMapping("/anos") */
    @ResponseBody
    public static String getAnos(String idMarca, String idModelo, String ano) {
        LinkedHashMap<String,String> anos = cliente
            .get()
            .uri("/fipe/api/v1/carros/marcas/"+idMarca+"/modelos/"+idModelo+"/anos")
            .retrieve()
            .body(LinkedHashMap.class);
        String idAno = null;
        Set<Map.Entry<String,String>> entrySet = anos.entrySet();
        Iterator<Map.Entry<String,String>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getValue().equals(ano)) {
                idAno = iterator.next().getKey();
                System.out.println("anos done");
                break;
            }
            iterator.next();
        }   
        return idAno;
    }   
}


