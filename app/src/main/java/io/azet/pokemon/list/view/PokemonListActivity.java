package io.azet.pokemon.list.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import java.util.ArrayList;

import io.azet.pokemon.details.view.PokemonDetailsActivity;
import io.azet.pokemon.list.model.PokemonUrl;
import io.azet.pokemon.list.model.database.FavouritesDatabase;
import io.azet.pokemon.list.presenter.PokemonListPresenter;
import io.azet.pokemon.list.view.PokemonListAdapter.OnPokemonItemClickListener;
import io.azet.pokemonUrl.databinding.ActivityListBinding;


public class PokemonListActivity extends AppCompatActivity implements PokemonListView, OnPokemonItemClickListener {

    public static final String EXTRA_POKEMON_URL = "pokemon_url";

    private PokemonListPresenter pokePresenter;
    private ActivityListBinding activityListBinding;
    private PokemonListAdapter pokemonListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityListBinding = ActivityListBinding.inflate(getLayoutInflater());
        setContentView(activityListBinding.getRoot());

        setupPokemonListAdapter();
        setupPokemonFilter();

        // todo: queries shouldn't be on main thread!
        FavouritesDatabase favDb = Room.databaseBuilder(getApplicationContext(),
                FavouritesDatabase.class, "favourites").allowMainThreadQueries().build();

        pokePresenter = new PokemonListPresenter(this, favDb.favouritesDao());
        pokePresenter.fetchPokemons();
    }

    private void setupPokemonFilter() {
        activityListBinding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                pokemonListAdapter.applyFilter(s.toString());
            }
        });
        activityListBinding.etSearch.setOnLongClickListener(v -> {
            pokemonListAdapter.clearFilter();
            activityListBinding.etSearch.setText("");
            return true;
        });
    }

    @Override
    public void updatePokemonList(ArrayList<PokemonUrl> pokemonUrls) {
        pokemonListAdapter.setPokemonUrls(pokemonUrls);
        pokemonListAdapter.notifyDataSetChanged();
    }

    @Override
    public void openPokemonDetailsActivity(PokemonUrl pokemonUrl) {
        Intent intent = new Intent(this, PokemonDetailsActivity.class);
        intent.putExtra(EXTRA_POKEMON_URL, pokemonUrl);
        startActivity(intent);
    }

    @Override
    public void onPokemonItemClick(PokemonUrl pokemonUrl) {
        pokePresenter.selectPokemon(pokemonUrl);
    }

    @Override
    public void onFavouriteClick(PokemonUrl pokemonUrl) {
        pokePresenter.toggleFavouritePokemon(pokemonUrl);
    }

    private void setupPokemonListAdapter() {
        pokemonListAdapter = new PokemonListAdapter( this);
        activityListBinding.rvPokemons.setLayoutManager(new LinearLayoutManager(this));
        activityListBinding.rvPokemons.setAdapter(pokemonListAdapter);
    }

}