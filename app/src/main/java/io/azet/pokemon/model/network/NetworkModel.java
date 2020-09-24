package io.azet.pokemon.model.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.azet.pokemon.model.Pokemon;
import io.azet.pokemon.model.PokemonDeserializer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkModel {

    private final static String URL = "https://pokeapi.co/api/v2/";

    public <T> T createService(Class<T> service) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Pokemon.class, new PokemonDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(service);
    }

}
