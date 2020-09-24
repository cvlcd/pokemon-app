package io.azet.pokemon.details.model.network;

import io.azet.pokemon.details.model.Description;
import io.azet.pokemon.details.model.Pokemon;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonDetailsService {

    @GET("pokemon/{name}")
    Observable<Pokemon> fetchPokemon(
            @Path("name") String name);

    @GET("pokemon-species/{name}")
    Observable<Description> fetchPokemonDescription(
            @Path("name") String name);
}
