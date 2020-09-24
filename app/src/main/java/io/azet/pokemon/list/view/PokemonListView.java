package io.azet.pokemon.list.view;

import java.util.ArrayList;

import io.azet.pokemon.list.model.PokemonUrl;

public interface PokemonListView {
    void updatePokemonList(ArrayList<PokemonUrl> pokemonUrls);
    void openPokemonDetailsActivity(PokemonUrl pokemonUrl);
}
