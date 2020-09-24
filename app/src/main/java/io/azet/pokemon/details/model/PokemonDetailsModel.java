package io.azet.pokemon.details.model;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface PokemonDetailsModel {

    Observable<Pokemon> fetchPokemonDetails();
    Observable<Description> fetchPokemonDescription(String name);

    String getName();
    boolean isFavourite();
    void setPokemon(Pokemon pokemon);
    int getWeight();
    int getHeight();
    String getSpriteUrl();
    List<String> getTypes();

}
