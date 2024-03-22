package com.opinno.ecommerce.service;

import com.opinno.ecommerce.model.Prodotto;
import com.opinno.ecommerce.persistence.ProdottoDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProdottoService {

    private ProdottoDAO dao;
    public ProdottoService(ProdottoDAO dao){
        this.dao = dao;
    }

    public Prodotto aggiungi(Prodotto p){
        // controlla se il prodotto p non sia già presente in ProdottoDAO
        if (dao.get(p.getId()) == null) {
            return dao.insert(p);
        }
        return null;
    }

    public boolean elimina(long id){
        return dao.delete(id);
    }

    public List<Prodotto> getProdottiDisponibili(){
        // Prendo tutti i prodotti
        List<Prodotto> tuttiProdotti = dao.getAll();
        List<Prodotto> prodottiDisponibili = new ArrayList<>();
        for(Prodotto prodotto : tuttiProdotti){
            // Controllo quelli con quantita > 0
            if (prodotto.isAvailable()){
                prodottiDisponibili.add(prodotto);
            }
        }
        return prodottiDisponibili;
    }

    // TODO finire di scrivere i metodi

    // TODO fare versione acquista che prende in input un prodotto
    public boolean acquista(long id, int quantita) throws RuntimeException{
        if(quantita <= 0){
            throw new RuntimeException("La quantità deve essere maggiore di zero");
        }
        Prodotto prodotto = dao.get(id);
        if(prodotto == null) {
            throw new RuntimeException("Prodotto non trovato");
        }

        if (prodotto.getQuantita() <=  quantita) {
            throw new RuntimeException("La quantità richiesta non è disponibile");
        }
        prodotto.setQuantita(prodotto.getQuantita()-quantita);
        if(dao.update(prodotto) == null){
            throw new RuntimeException("Impossibile acquistare il prodotto");
        }
        return true;
    }







    public boolean acquista(long id){
        // ogni volta che acquisto, ne acquisto solo 1 quantità
        Prodotto prodotto = dao.get(id);
        if (prodotto.isAvailable()) {
            prodotto.setQuantita(prodotto.getQuantita()-1);
            return true;
        }
        return false;
    }



    public Prodotto get(long id){
        Prodotto prodotto = new Prodotto();
        prodotto = dao.get(id);
        return prodotto;
    }

    public List<Prodotto> getAll(){
        return dao.getAll();
    }




}
