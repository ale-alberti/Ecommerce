package com.opinno.ecommerce;


import com.opinno.ecommerce.controller.ProdottoController;
import com.opinno.ecommerce.model.Prodotto;
import com.opinno.ecommerce.persistence.ProdottoDAO;
import com.opinno.ecommerce.persistence.ProdottoDaoCollection;
import com.opinno.ecommerce.service.ProdottoService;
;
public class App
{
    public static void main( String[] args )
    {

        Prodotto prodotto1 = Prodotto.builder()
                .id(1)
                .nome("Airpods")
                .descrizione("Cuffie costosissime maledetta Apple")
                .quantita(10)
                .prezzo(289).build();

        Prodotto prodotto2 = Prodotto.builder()
                .id(2)
                .nome("iPad")
                .descrizione("Tablet costossissimo maledetta Apple")
                .quantita(20)
                .prezzo(489).build();


        ProdottoDAO prodottiInMemoria = new ProdottoDaoCollection();

        prodottiInMemoria.insert(prodotto1);
        prodottiInMemoria.insert(prodotto2);
        // System.out.println("Elenco prodotti in Collection: " +prodottiInMemoria.getAll());

        // avvio il Server
        ProdottoService service = new ProdottoService(prodottiInMemoria);
        ProdottoController prodottoController = new ProdottoController(service);
    }
}
