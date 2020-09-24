package io.azet.pokemon.presenters;

import android.util.Log;

import io.azet.pokemon.model.PokemonModel;
import io.azet.pokemon.model.PokemonModelImpl;
import io.azet.pokemon.view.PokemonView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PokemonPresenter {

    private PokemonView pokeView;
    private PokemonModel pokeModel;


    public PokemonPresenter(PokemonView pokeView) {
        this.pokeView = pokeView;
        pokeModel = new PokemonModelImpl();
    }

    public void fetchPokemons() {
        pokeModel.fetchFirstPokemonBatch()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(firstResponse -> {
                    pokeModel.addPokemonUrls(firstResponse.getResults());
                    // pokeView.updatePokemonList(pokeModel.getPokemonUrls());
                    pokeView.showPokemonList(pokeModel.getPokemonUrls());
                    firstResponse.getResults().forEach(pokemonUrl -> Log.d("Pokemon", pokemonUrl.getName()));
                });

    }

}
