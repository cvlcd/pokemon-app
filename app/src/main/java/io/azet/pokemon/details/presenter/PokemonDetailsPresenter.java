package io.azet.pokemon.details.presenter;

import io.azet.pokemon.details.model.PokemonDetailsModel;
import io.azet.pokemon.details.model.PokemonDetailsModelImpl;
import io.azet.pokemon.details.view.PokemonDetailsView;
import io.azet.pokemon.list.model.PokemonUrl;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PokemonDetailsPresenter {

    private PokemonDetailsView pokemonDetailsView;
    private PokemonDetailsModel pokemonDetailsModel;

    public PokemonDetailsPresenter(PokemonDetailsView pokemonDetailsView, PokemonUrl pokemonUrl) {
        this.pokemonDetailsView = pokemonDetailsView;
        pokemonDetailsModel = new PokemonDetailsModelImpl(pokemonUrl);
    }

    public void fetchPokemonDetails() {
        pokemonDetailsView.updatePokemonBase(pokemonDetailsModel.isFavourite(), pokemonDetailsModel.getName());

        pokemonDetailsModel.fetchPokemonDetails()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                // todo: disposable should be disposed onDestroy!
                .subscribe(pokemon -> {
                    pokemonDetailsModel.setPokemon(pokemon);
                    pokemonDetailsView.updatePokemonDetails(pokemonDetailsModel.getHeight(),
                            pokemonDetailsModel.getWeight(), pokemonDetailsModel.getTypes());
                    pokemonDetailsView.updatePokemonSprite(pokemonDetailsModel.getSpriteUrl());
                });

        pokemonDetailsModel.fetchPokemonDescription(pokemonDetailsModel.getName())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                // todo: disposable should be disposed onDestroy!
                .subscribe(description -> {
                    pokemonDetailsView.updatePokemonDescription(description.getFlavorText());
                });
    }

}
