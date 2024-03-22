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

    public boolean acquista(long id, int quantita){


        Prodotto prodotto = dao.get(id);
        if (prodotto.getQuantita() > quantita) {
            prodotto.setQuantita(prodotto.getQuantita()-quantita);
            return true;
        }
        return false;
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
