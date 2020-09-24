package io.azet.pokemon.view;

import androidx.fragment.app.Fragment;

import io.azet.pokemon.model.Pokemon;

public class PokemonDetailsFragment extends Fragment {

    public static PokemonDetailsFragment newInstance(Pokemon pokemon) {
        PokemonDetailsFragment fragment = new PokemonDetailsFragment();
        return  fragment;
    }

}
