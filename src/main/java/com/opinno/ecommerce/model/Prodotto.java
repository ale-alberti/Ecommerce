package com.opinno.ecommerce.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Prodotto {
    long id;
    String nome;
    String descrizione;
    int quantita;
    double prezzo;

    public boolean isAvailable(){
        return quantita > 0;
    }
}

