package io.azet.pokemon.model.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import io.azet.pokemon.model.PokemonUrl;

@Database(entities = {PokemonUrl.class}, version = 1)
public abstract class FavourtiesDatabase extends RoomDatabase {
    public abstract FavouritesDao favouritesDao();
}
