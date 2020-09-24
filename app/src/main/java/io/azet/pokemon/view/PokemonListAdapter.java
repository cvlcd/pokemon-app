package io.azet.pokemon.view;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.azet.pokemon.model.PokemonUrl;
import io.azet.pokemonUrl.R;
import io.azet.pokemonUrl.databinding.ItemPokemonBinding;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> {

    private ArrayList<PokemonUrl> pokemonUrls;
    private OnPokemonItemClickListener onPokemonItemClickListener;

    public interface OnPokemonItemClickListener {
        void onPokemonItemClick(PokemonUrl item);
        void onFavouriteClick(PokemonUrl item);
    }

    public PokemonListAdapter(ArrayList<PokemonUrl> pokemonUrls,
                              OnPokemonItemClickListener onPokemonItemClickListener) {
        this.pokemonUrls = pokemonUrls;
        this.onPokemonItemClickListener = onPokemonItemClickListener;
    }

    @NonNull
    @Override
    public PokemonListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(v);
    }

    @Override
    @SuppressLint("DefaultLocale")
    public void onBindViewHolder(@NonNull PokemonListAdapter.ViewHolder holder, int position) {
        holder.itemPokemonBinding.tvIndex.setText(String.format("#%03d", position + 1));
        holder.itemPokemonBinding.tvName.setText(pokemonUrls.get(position).getName());

        holder.itemPokemonBinding.clItem.setOnClickListener(v -> {
            onPokemonItemClickListener.onPokemonItemClick(pokemonUrls.get(position));
        });

        holder.itemPokemonBinding.ivFavourite.setOnClickListener(v -> {
            onPokemonItemClickListener.onFavouriteClick(pokemonUrls.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return pokemonUrls.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ItemPokemonBinding itemPokemonBinding;

        public ViewHolder(@NonNull View view) {
            super(view);
            itemPokemonBinding = ItemPokemonBinding.bind(view);
        }
    }
}
