package io.azet.pokemon.list.model.network;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface PokemonService {

    String OFFSET = "offset";
    String LIMIT = "limit";

    public static final int MAX_BATCH_LIMIT = 50;

    @GET("pokemon/")
    Observable<PokemonResponse> fetchPokemonBatch(
            @Query(OFFSET) int offset,
            @Query(LIMIT) int limit);

    @GET
    Observable<PokemonResponse> fetchPokemonBatch(
            @Url String url);

}
