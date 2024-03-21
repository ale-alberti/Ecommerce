package com.opinno.ecommerce.controller;

import com.google.gson.Gson;
import com.opinno.ecommerce.model.Prodotto;
import com.opinno.ecommerce.persistence.ProdottoDAO;
import com.opinno.ecommerce.service.ProdottoService;
import com.opinno.ecommerce.utility.HttpResponse;


import static spark.Spark.get;

public class ProdottoController {

    ProdottoService service;

    public ProdottoController(ProdottoService service){
        this.service = service;
    }

    public void startWebServices(){
        // http://localhost:4567/getProduct?=id


        // http://localhost:4567/product/1
        get("/product/:id" , (req, res) ->{
            res.type("application/json");

            Long id = Long.parseLong(req.params("id"));

            Prodotto prodotto = service.get(id);

            HttpResponse response = new HttpResponse("200", new Gson().toJsonTree(prodotto));

            return new Gson().toJson(response);
        });



    }
}
