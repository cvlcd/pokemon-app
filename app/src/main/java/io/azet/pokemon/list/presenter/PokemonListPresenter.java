package io.azet.pokemon.list.presenter;

import java.util.List;

import io.azet.pokemon.list.model.PokemonListModel;
import io.azet.pokemon.list.model.PokemonListModelImpl;
import io.azet.pokemon.list.model.PokemonUrl;
import io.azet.pokemon.list.model.database.FavouritesDao;
import io.azet.pokemon.list.view.PokemonListView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PokemonListPresenter {

    private PokemonListView pokemonListView;
    private PokemonListModel pokemonListModel;
    private FavouritesDao favouritesDao;

    public PokemonListPresenter(PokemonListView pokemonListView, FavouritesDao favouritesDao) {
        this.pokemonListView = pokemonListView;
        this.favouritesDao = favouritesDao;
        pokemonListModel = new PokemonListModelImpl();
    }

    public void fetchPokemons() {
        pokemonListModel.fetchFirstPokemonBatch()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                // todo: disposable should be disposed onDestroy!
                .subscribe(firstResponse -> {
                    List<PokemonUrl> pokemonUrls = firstResponse.getResults();
                    pokemonUrls.stream().filter(this::isPokemonInFavouriteDb)
                            .forEach(PokemonUrl::toggleFavourite);
                    pokemonListModel.addPokemonUrls(firstResponse.getResults());
                    // pokeView.updatePokemonList(pokeModel.getPokemonUrls());
                    pokemonListView.updatePokemonList(pokemonListModel.getPokemonUrls());
                });
    }

    public void toggleFavouritePokemon(PokemonUrl pokemonUrl) {
        pokemonListModel.togglePokemonUrlsFavourite(pokemonUrl);
        if (isPokemonInFavouriteDb(pokemonUrl)) {
            favouritesDao.deletePokemon(pokemonUrl.getName());
        } else {
            favouritesDao.insertFavourite(pokemonUrl);
        }
        pokemonListView.updatePokemonList(pokemonListModel.getPokemonUrls());
    }

    private boolean isPokemonInFavouriteDb(PokemonUrl pokemonUrl) {
       return favouritesDao.getFavourites().stream()
               .anyMatch(favourite -> favourite.getName().equals(pokemonUrl.getName()));
    }

    public void selectPokemon(PokemonUrl pokemonUrl) {
        pokemonListView.openPokemonDetailsActivity(pokemonUrl);
    }
}
