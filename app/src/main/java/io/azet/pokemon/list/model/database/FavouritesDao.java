package io.azet.pokemon.list.model.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.azet.pokemon.list.model.PokemonUrl;

@Dao
public interface FavouritesDao {

    @Query("SELECT * FROM favourites")
    List<PokemonUrl> getFavourites();

    @Insert
    void insertFavourite(PokemonUrl pokemonUrl);

    @Query("DELETE FROM favourites WHERE name = :name")
    void deletePokemon(String name);

}
