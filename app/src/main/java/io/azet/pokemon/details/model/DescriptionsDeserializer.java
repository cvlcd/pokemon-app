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

public class DescriptionsDeserializer implements JsonDeserializer<Description> {
    @Override
    public Description deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        ArrayList<Description> descriptions = new ArrayList<>();
        JsonArray jsonFlavourTextEntries = jsonObject.getAsJsonArray("flavor_text_entries");
        jsonFlavourTextEntries.forEach(jsonElement -> {
            String language = jsonElement.getAsJsonObject().getAsJsonObject("language").get("name").getAsString();
            String flavorText = jsonElement.getAsJsonObject().get("flavor_text").getAsString();
            descriptions.add(new Description(flavorText, language));
        });

        return descriptions.stream().filter(description -> description.getLanguage().equals("en")).findFirst().orElse(null);
    }
}
