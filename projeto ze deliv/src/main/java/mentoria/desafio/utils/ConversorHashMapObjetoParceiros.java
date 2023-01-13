package mentoria.desafio.utils;

import mentoria.desafio.model.Parceiros;

import java.util.HashMap;

public class ConversorHashMapObjetoParceiros {

    public Parceiros converter(HashMap body) {

        Parceiros parceiros = Parceiros.builder()
                .id(Integer.parseInt(body.get("id").toString()))
                .tradingName(body.get("tradingName").toString())
                .ownerName(body.get("ownerName").toString())
                .document(body.get("document").toString())
                .coverageArea(body.get("coverageArea").toString())
                .addressCoordinates(body.get("addressCoordinates").toString())
                .build();


        return parceiros;

    }
}

