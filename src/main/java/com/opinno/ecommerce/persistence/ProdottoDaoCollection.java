package com.opinno.ecommerce.persistence;

import com.opinno.ecommerce.model.Prodotto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdottoDaoCollection implements ProdottoDAO{

    Map<Long, Prodotto> mappaProdotti = new HashMap<>();
    @Override
    public Prodotto insert(Prodotto p) {
        // TODO controllare che l'id non sia già presente
        if(!mappaProdotti.containsKey(p.getId())) {
            mappaProdotti.put(p.getId(), p);
            return p;
        }
        return null;
    }

    @Override
    public Prodotto update(Prodotto p) {
        return insert(p);
    }

    @Override
    public boolean delete(long id) {
        if (mappaProdotti.remove(id) != null){
            return true;
        };
        return false;
    }

    @Override
    public Prodotto get(long id) {
        return mappaProdotti.get(id);
    }

    @Override
    public List<Prodotto> getAll() {
        // sembra funzionare!!! ehhh!!!!!
        List<Prodotto> listaProdotti = new ArrayList<>();
        for (Map.Entry<Long, Prodotto> mappa : mappaProdotti.entrySet()){
            listaProdotti.add(mappa.getValue());
        }
        return listaProdotti;
    }
}
