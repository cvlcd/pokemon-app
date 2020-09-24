package io.azet.pokemon.model;

import java.util.ArrayList;

import io.azet.pokemon.model.network.PokemonResponse;
import io.reactivex.rxjava3.core.Observable;

public interface PokemonModel {
    
    ArrayList<PokemonUrl> pokemonUrls = new ArrayList<>();

    Observable<PokemonResponse> fetchFirstPokemonBatch();
    Observable<PokemonResponse> fetchPokemonBatch(String url);

    void addPokemonUrls(ArrayList<PokemonUrl> results);
    ArrayList<PokemonUrl> getPokemonUrls();
}
