package com.opinno.ecommerce.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Prodotto {
    @NonNull
    long id;
    String nome;
    String descrizione;
    int quantita;
    double prezzo;

    public boolean isAvailable(){
        return quantita > 0;
    }
}

