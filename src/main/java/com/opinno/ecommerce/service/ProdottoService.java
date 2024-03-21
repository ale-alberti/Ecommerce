package com.opinno.ecommerce.service;

import com.opinno.ecommerce.model.Prodotto;
import com.opinno.ecommerce.persistence.ProdottoDAO;

import java.util.ArrayList;
import java.util.List;

public class ProdottoService {

    ProdottoDAO dao;
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

    public boolean elimina(Prodotto p){
        return dao.delete(p.getId());
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

    public void acquista(Prodotto p, int quantita){
        throw new RuntimeException("Da implementare");
    }

    public Prodotto get(long id){
        throw new RuntimeException("Da implementare");
    }



}
