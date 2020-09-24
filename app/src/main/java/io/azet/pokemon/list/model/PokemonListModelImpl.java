package io.azet.pokemon.list.model;

import java.util.ArrayList;

import io.azet.pokemon.common.NetworkModel;
import io.azet.pokemon.list.model.network.PokemonResponse;
import io.azet.pokemon.list.model.network.PokemonService;
import io.reactivex.rxjava3.core.Observable;

import static io.azet.pokemon.list.model.network.PokemonService.MAX_BATCH_LIMIT;

public class PokemonListModelImpl implements PokemonListModel {

    ArrayList<PokemonUrl> pokemonUrls = new ArrayList<>();
    PokemonService pokemonService;

    public PokemonListModelImpl() {
        pokemonService = new NetworkModel().createService(PokemonService.class);
    }

    @Override
    public Observable<PokemonResponse> fetchFirstPokemonBatch() {
        return fetchPokemonBatch(0, MAX_BATCH_LIMIT);
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
    public void togglePokemonUrlsFavourite(PokemonUrl pokemonUrl) {
        pokemonUrls.stream().filter(pUrl -> pUrl.getName().equals(pokemonUrl.getName()))
                .forEach(PokemonUrl::toggleFavourite);
    }

    @Override
    public ArrayList<PokemonUrl> getPokemonUrls() {
        return pokemonUrls;
    }

    private Observable<PokemonResponse> fetchPokemonBatch(int offset, int limit) {
        return pokemonService.fetchPokemonBatch(offset, limit);
    }

}
