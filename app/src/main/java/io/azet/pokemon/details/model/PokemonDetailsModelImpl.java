package io.azet.pokemon.details.model;

import java.util.List;

import io.azet.pokemon.common.NetworkModel;
import io.azet.pokemon.details.model.PokemonDetailsModel;
import io.azet.pokemon.details.model.network.PokemonDetailsService;
import io.azet.pokemon.list.model.PokemonUrl;
import io.azet.pokemon.list.model.network.PokemonService;
import io.reactivex.rxjava3.core.Observable;

public class PokemonDetailsModelImpl implements PokemonDetailsModel {

    private PokemonDetailsService pokemonDetailsService;
    private PokemonUrl pokemonUrl;
    private Pokemon pokemon;

    public PokemonDetailsModelImpl(PokemonUrl pokemonUrl) {
        this.pokemonUrl = pokemonUrl;
        pokemonDetailsService = new NetworkModel().createService(PokemonDetailsService.class);
    }

    @Override
    public Observable<Pokemon> fetchPokemonDetails() {
        return pokemonDetailsService.fetchPokemon(pokemonUrl.getName());
    }

    @Override
    public Observable<Description> fetchPokemonDescription(String name) {
        return pokemonDetailsService.fetchPokemonDescription(name);
    }

    @Override
    public String getName() {
        return pokemonUrl.getName();
    }

    @Override
    public boolean isFavourite() {
        return pokemonUrl.isFavourite();
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    @Override
    public int getWeight() {
        return pokemon.getWeight();
    }

    @Override
    public int getHeight() {
        return pokemon.getHeight();
    }

    @Override
    public String getSpriteUrl() {
        return pokemon.getSpriteUrl();
    }

    @Override
    public List<String> getTypes() {
        return pokemon.getTypes();
    }

}
