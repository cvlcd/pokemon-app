package io.azet.pokemon.list.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "favourites")
public class PokemonUrl implements Parcelable {

    @PrimaryKey
    @NonNull
    private String name;
    private String url;
    @Ignore
    private boolean isFavourite;

    public PokemonUrl() { }

    public PokemonUrl(String name, String url, boolean isFavourite) {
        this.name = name;
        this.url = url;
        this.isFavourite = isFavourite;
    }

    protected PokemonUrl(Parcel in) {
        name = in.readString();
        url = in.readString();
        isFavourite = in.readBoolean();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void toggleFavourite() {
        isFavourite = !isFavourite;
    }

    public static final Creator<PokemonUrl> CREATOR = new Creator<PokemonUrl>() {
        @Override
        public PokemonUrl createFromParcel(Parcel in) {
            return new PokemonUrl(in);
        }

        @Override
        public PokemonUrl[] newArray(int size) {
            return new PokemonUrl[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.url);
        dest.writeBoolean(isFavourite);
    }
}
