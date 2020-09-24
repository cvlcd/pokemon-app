package io.azet.pokemon.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.azet.pokemon.model.Pokemon;
import io.azet.pokemon.model.PokemonUrl;
import io.azet.pokemon.presenters.PokemonPresenter;
import io.azet.pokemon.view.PokemonListAdapter.OnPokemonItemClickListener;
import io.azet.pokemonUrl.R;
import io.azet.pokemonUrl.databinding.ActivityBaseBinding;


public class PokemonActivity extends AppCompatActivity implements PokemonView, OnPokemonItemClickListener {

    private PokemonPresenter pokePresenter;
    private ActivityBaseBinding activityBaseBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBaseBinding = ActivityBaseBinding.inflate(getLayoutInflater());
        setContentView(activityBaseBinding.getRoot());

        pokePresenter = new PokemonPresenter(this);
        pokePresenter.fetchPokemons();
    }

    @Override
    public void showPokemonList(ArrayList<PokemonUrl> pokemonUrls) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, PokemonListFragment.newInstance(pokemonUrls))
                .commit();
    }

    @Override
    public void showPokemonDetails(Pokemon pokemon) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, PokemonDetailsFragment.newInstance(pokemon))
                .commit();
    }

    @Override
    public void updatePokemonList(List<PokemonUrl> pokemonUrls) {
        //
    }

    private boolean isPokemonListVisible() {
        return getSupportFragmentManager().findFragmentById(R.id.container) instanceof PokemonListFragment;
    }

    @Override
    public void onPokemonItemClick(PokemonUrl pokemonUrl) {
        Log.d("Pokemon", "click" + pokemonUrl.getName());
//        pokePresenter.selectPokemon(pokemonUrl);
    }

    @Override
    public void onFavouriteClick(PokemonUrl pokemonUrl) {
        Log.d("Pokemon", "fav" + pokemonUrl.getName());
//        pokePresenter.addPokemonAsFavourite(pokemonUrl);
    }
}