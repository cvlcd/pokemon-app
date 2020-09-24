package io.azet.pokemon.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.azet.pokemon.details.model.Description;
import io.azet.pokemon.details.model.DescriptionsDeserializer;
import io.azet.pokemon.details.model.Pokemon;
import io.azet.pokemon.details.model.PokemonDeserializer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

// todo: this can be injected when using DI, e.g. Hilt
public class NetworkModel {

    private final static String URL = "https://pokeapi.co/api/v2/";

    public <T> T createService(Class<T> service) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Pokemon.class, new PokemonDeserializer())
                .registerTypeAdapter(Description.class, new DescriptionsDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(service);
    }

}
