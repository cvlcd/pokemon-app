package io.azet.pokemon.details.view;

import java.util.List;

public interface PokemonDetailsView {
    void updatePokemonBase(boolean isFavourite, String name);
    void updatePokemonDetails(int height, int weight, List<String> types);
    void updatePokemonDescription(String description);
    void updatePokemonSprite(String url);

}
