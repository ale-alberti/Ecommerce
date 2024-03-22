package com.opinno.ecommerce.controller;

import com.google.gson.Gson;
import com.opinno.ecommerce.model.Prodotto;
import com.opinno.ecommerce.persistence.ProdottoDAO;
import com.opinno.ecommerce.service.ProdottoService;
import com.opinno.ecommerce.utility.HttpResponse;

import java.util.List;

import static spark.Spark.*;

public class ProdottoController {

    ProdottoService service;

    public ProdottoController(ProdottoService service){
        this.service = service;
        startWebServices();
    }

    private void startWebServices(){
        // http://localhost:4567/getProduct?=id


        // http://localhost:4567/product/1
        get("/product/:id" , (req, res) ->{
            res.type("application/json");

            long id = Long.parseLong(req.params("id"));

            Prodotto prodotto = service.get(id);

            HttpResponse response = new HttpResponse("200", new Gson().toJsonTree(prodotto));

            return new Gson().toJson(response);
        });

        get("/products" , (req, res) ->{
            res.type("application/json");

            List<Prodotto> elencoProdotti = service.getAll();

            HttpResponse response = new HttpResponse("200", new Gson().toJsonTree(elencoProdotti));

            return new Gson().toJson(response);
        });


        post("/product", (req, res) ->{
            res.type("application/json");
            Prodotto prodottoFromReq = new Gson().fromJson(req.body(), Prodotto.class);
            service.aggiungi(prodottoFromReq);
            HttpResponse response = new HttpResponse("201", prodottoFromReq);
            return new Gson().toJson(response);
        });

        delete("/product/:productID", (req, res) -> {
            boolean operazione;
            String s = req.params("productID");
            long paramID = Long.parseLong(s);

            // TODO sistemare medoto elimina che prenda un id e non un oggetto
            operazione = service.elimina(paramID);
            HttpResponse response;
            if (operazione){
                response = new HttpResponse("200", "Prodotto cancellato");
            }
            else {
                response = new HttpResponse("500", "Prodotto non cancellato");
            }
            return new Gson().toJson(response);
        });

        put("/product/:productID", (req, res) -> {
            // acquista un solo prodotto alla volta
            // http://localhost:4567/product/getProductID?=1 & getQuantita?=5
            String s = req.params("productID");
            long paramID = Long.parseLong(s);
            boolean controllo = false;
            controllo = service.acquista(paramID);
            HttpResponse response;
            if (controllo) {
                response = new HttpResponse("200", "Prodotto acquistato");
            }
            else {
                response = new HttpResponse("404", "Prodotto terminato");
            }
            return new Gson().toJson(response);
        });

//        put("/product/:productID/:qty", (req, res) -> {
//            // acquista un solo prodotto alla volta
//            // http://localhost:4567/product?ProductID=1 & qty=5
//            String s = req.params("productID");
//            long paramID = Long.parseLong(s);
//            String t = req.params("qty");
//            int paramQuantita = Integer.parseInt(t);
//            boolean controllo = false;
//            controllo = service.acquista(paramID, paramQuantita);
//            HttpResponse response;
//            if (controllo) {
//                response = new HttpResponse("200", "Prodotto acquistato");
//            }
//            else {
//                response = new HttpResponse("404", "Prodotto terminato");
//            }
//            return new Gson().toJson(response);
//        });



//        put("/product", (req, res) -> {
//            // acquista un solo prodotto alla volta
//            // http://localhost:4567/product?ProductID=1&qty=3
//            String s = req.queryParams("productID");
//            long paramID = Long.parseLong(s);
//            String t = req.queryParams("qty");
//            int paramQuantita = Integer.parseInt(t);
//            boolean controllo = false;
//            controllo = service.acquista(paramID, paramQuantita);
//            HttpResponse response;
//            if (controllo) {
//                response = new HttpResponse("200", "Prodotto acquistato");
//            } else {
//                response = new HttpResponse("404", "Prodotto terminato");
//            }
//            return new Gson().toJson(response);
//        });

        put("/product", (req, res) -> {

            String t = req.queryParams("qty");
            int paramQuantita = Integer.parseInt(t);
            Prodotto prodottoFromReq = new Gson().fromJson(req.body(), Prodotto.class);
            boolean controllo = false;
            controllo = service.acquista(prodottoFromReq.getId(), paramQuantita);
            HttpResponse response;
            if (controllo) {
                response = new HttpResponse("200", "Prodotto acquistato");
            } else {
                response = new HttpResponse("404", "Prodotto terminato");
            }
            return new Gson().toJson(response);
        });






    }
}
