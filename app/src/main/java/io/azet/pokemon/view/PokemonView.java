package io.azet.pokemon.view;

import java.util.ArrayList;
import java.util.List;

import io.azet.pokemon.model.Pokemon;
import io.azet.pokemon.model.PokemonUrl;

public interface PokemonView {

    void showPokemonList(ArrayList<PokemonUrl> pokemonUrls);
    void showPokemonDetails(Pokemon pokemon);

    void updatePokemonList(List<PokemonUrl> pokemonUrls);
}
