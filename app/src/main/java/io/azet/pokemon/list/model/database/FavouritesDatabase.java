package io.azet.pokemon.list.model.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import io.azet.pokemon.list.model.PokemonUrl;

@Database(entities = {PokemonUrl.class}, version = 1)
public abstract class FavouritesDatabase extends RoomDatabase {
    public abstract FavouritesDao favouritesDao();
}
