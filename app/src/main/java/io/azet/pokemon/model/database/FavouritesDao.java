package io.azet.pokemon.model.database;

import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.azet.pokemon.model.PokemonUrl;

public interface FavouritesDao {

    @Query("SELECT * FROM favourites")
    List<PokemonUrl> getFavourites();

    @Insert
    void insertFavourite(PokemonUrl pokemonUrl);

    @Query("DELETE FROM favourites WHERE name = :name")
    void deletePokemon(String name);

}
