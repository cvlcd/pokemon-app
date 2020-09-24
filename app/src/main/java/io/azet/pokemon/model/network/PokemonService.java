package io.azet.pokemon.model.network;

import io.azet.pokemon.model.Pokemon;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface PokemonService {

    String OFFSET = "offset";
    String LIMIT = "limit";

    static final int MAX_LIMIT = 50;

    @GET("pokemon/")
    Observable<PokemonResponse> fetchPokemonBatch(
            @Query(OFFSET) int offset,
            @Query(LIMIT) int limit);

    @GET
    Observable<PokemonResponse> fetchPokemonBatch(
            @Url String url);

    @GET("pokemon/{name}")
    Observable<Pokemon> fetchPokemon(
            @Path("name") String name);

    @GET("pokemon-species/{name}")
    Observable<String> fetchPokemonDescription(
            @Path("name") String name);

}
