package io.azet.pokemon.list.model.network;

import java.util.ArrayList;

import io.azet.pokemon.list.model.PokemonUrl;

public class PokemonResponse {
    private int count;
    private String next;
    private String previous;
    private ArrayList<PokemonUrl> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public ArrayList<PokemonUrl> getResults() {
        return results;
    }

    public void setResults(ArrayList<PokemonUrl> results) {
        this.results = results;
    }

}
