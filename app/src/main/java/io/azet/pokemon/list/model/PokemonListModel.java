package io.azet.pokemon.list.model;

import java.util.ArrayList;

import io.azet.pokemon.list.model.network.PokemonResponse;
import io.reactivex.rxjava3.core.Observable;

public interface PokemonListModel {

    Observable<PokemonResponse> fetchFirstPokemonBatch();
    Observable<PokemonResponse> fetchPokemonBatch(String url);

    void addPokemonUrls(ArrayList<PokemonUrl> results);
    void togglePokemonUrlsFavourite(PokemonUrl pokemonUrl);

    ArrayList<PokemonUrl> getPokemonUrls();
}
