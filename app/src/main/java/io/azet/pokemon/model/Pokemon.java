package io.azet.pokemon.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pokemon implements Parcelable {
    private int height;
    private int weight;
    private Map<String, String> speciesByUrl;
    // for simplification using only "other/dream_world" sprite if available, nullable
    private String spriteUrl;
    private List<String> types;

    public Pokemon(int height, int weight, Map<String, String> speciesByUrl,
                   String spriteUrl, List<String> types) {
        this.height = height;
        this.weight = weight;
        this.speciesByUrl = speciesByUrl;
        this.spriteUrl = spriteUrl;
        this.types = types;
    }

    protected Pokemon(Parcel in) {
        height = in.readInt();
        weight = in.readInt();
        speciesByUrl = new HashMap<>();
        in.readMap(speciesByUrl, HashMap.class.getClassLoader());
        spriteUrl = in.readString();
        types = new ArrayList<>();
        in.readList(types, String.class.getClassLoader());
    }

    public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(height);
        dest.writeInt(weight);
        dest.writeMap(speciesByUrl);
        dest.writeString(spriteUrl);
        dest.writeList(types);
    }
}
