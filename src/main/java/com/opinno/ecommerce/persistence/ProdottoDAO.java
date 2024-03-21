package com.opinno.ecommerce.persistence;

import com.opinno.ecommerce.model.Prodotto;

import java.util.List;

public interface ProdottoDAO {

    Prodotto insert(Prodotto p);
    Prodotto update(Prodotto p);
    boolean delete(long id);
    Prodotto get(long id);
    List<Prodotto> getAll();

}
