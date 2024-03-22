package com.opinno.ecommerce.utility;

import com.google.gson.JsonElement;
import com.opinno.ecommerce.model.Prodotto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class HttpResponse {
    private String statusResponse;
    private JsonElement data;
    private String description;
    private Prodotto prodotto;


    public HttpResponse(String statusResponse, String description){
        this.statusResponse = statusResponse;
        this.description = description;
    }

    public HttpResponse(String statusResponse, Prodotto prodotto){
        this.statusResponse = statusResponse;
        this.description = description;
    }

    public HttpResponse(String statusResponse, JsonElement data){
        this.statusResponse = statusResponse;
        this.data = data;
    }







}



