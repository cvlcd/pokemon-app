package io.azet.pokemon.details.model;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.azet.pokemon.details.model.Pokemon;

public class PokemonDeserializer implements JsonDeserializer<Pokemon> {
    @Override
    public Pokemon deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        if (!jsonObject.has("weight") || !jsonObject.has("height")) {
            // todo: better approach would be to throw & catch exceptions
            Log.e("PokeDeserializer", "missing at least one of keys: weight, height");
            return null;
        }

        int weight = jsonObject.get("weight").getAsInt();
        int height = jsonObject.get("height").getAsInt();

        // todo: null checking of species and if following entries are not null
        Map<String, String> speciesByUrl = new HashMap<>();
        JsonObject speciesObject = jsonObject.get("species").getAsJsonObject();
        speciesObject.entrySet().forEach(
                entry -> speciesByUrl.put(entry.getKey(), entry.getValue().getAsString()));

        // todo: null checking sprites structure
        String spriteUrl = jsonObject
                .getAsJsonObject("sprites")
                .getAsJsonObject("other")
                .getAsJsonObject("dream_world")
                .get("front_default").getAsString();


        // todo: null checking of types structure
        List<String> types = new ArrayList<>();
        JsonArray jsonTypes = jsonObject.getAsJsonArray("types");
        jsonTypes.forEach(
                jsonElement -> {
                    JsonObject jsonTypeObject = jsonElement.getAsJsonObject();
                    types.add(jsonTypeObject.getAsJsonObject("type")
                            .get("name").getAsString());
                });

        return new Pokemon(weight, height, speciesByUrl, spriteUrl, types);
    }

}
