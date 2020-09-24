package io.azet.pokemon.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favourites")
public class PokemonUrl implements Parcelable {

    @PrimaryKey
    private String name;
    private String url;

    protected PokemonUrl(Parcel in) {
        name = in.readString();
        url = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.url);
    }
}
