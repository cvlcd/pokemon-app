package io.azet.pokemon.model;

import java.util.ArrayList;

import io.azet.pokemon.model.database.FavourtiesDatabase;
import io.azet.pokemon.model.network.NetworkModel;
import io.azet.pokemon.model.network.PokemonResponse;
import io.azet.pokemon.model.network.PokemonService;
import io.reactivex.rxjava3.core.Observable;

public class PokemonModelImpl implements PokemonModel {

    PokemonService pokemonService;
    FavourtiesDatabase favDb;

    public PokemonModelImpl() {
        pokemonService = new NetworkModel().createService(PokemonService.class);
    }

    @Override
    public Observable<PokemonResponse> fetchFirstPokemonBatch() {
        return fetchPokemonBatch(0, 50);
    }

    @Override
    public Observable<PokemonResponse> fetchPokemonBatch(String url) {
        return pokemonService.fetchPokemonBatch(url);
    }

    @Override
    public void addPokemonUrls(ArrayList<PokemonUrl> results) {
        pokemonUrls.addAll(results);
    }

    @Override
    public ArrayList<PokemonUrl> getPokemonUrls() {
        return pokemonUrls;
    }

    private Observable<PokemonResponse> fetchPokemonBatch(int offset, int limit) {
        return pokemonService.fetchPokemonBatch(offset, limit);
    }

}
