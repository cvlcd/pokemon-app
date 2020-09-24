package io.azet.pokemon.list.view;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.azet.pokemon.list.model.PokemonUrl;
import io.azet.pokemonUrl.R;
import io.azet.pokemonUrl.databinding.ItemPokemonBinding;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> {

    private ArrayList<PokemonUrl> displayPokemonUrls = new ArrayList<>();
    private ArrayList<PokemonUrl> pokemonUrls = new ArrayList<>();

    private OnPokemonItemClickListener onPokemonItemClickListener;

    public interface OnPokemonItemClickListener {
        void onPokemonItemClick(PokemonUrl item);
        void onFavouriteClick(PokemonUrl item);
    }

    public PokemonListAdapter(OnPokemonItemClickListener onPokemonItemClickListener) {
        this.onPokemonItemClickListener = onPokemonItemClickListener;
    }

    public void setPokemonUrls(ArrayList<PokemonUrl> pokemonUrls) {
        this.pokemonUrls = pokemonUrls;
        this.displayPokemonUrls = pokemonUrls;
    }

    public void clearFilter() {
        displayPokemonUrls = pokemonUrls;
        notifyDataSetChanged();
    }

    public void applyFilter(String filter) {
        displayPokemonUrls = pokemonUrls.stream()
                .filter(pokemonUrl -> pokemonUrl.getName().contains(filter))
                .collect(Collectors.toCollection(ArrayList::new));
        notifyDataSetChanged();
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
        PokemonUrl pokemonUrl = displayPokemonUrls.get(position);

        holder.itemPokemonBinding.ivFavourite.setImageResource(pokemonUrl.isFavourite() ?
                R.drawable.ic_favourite : R.drawable.ic_favourite_border);
        holder.itemPokemonBinding.tvIndex
                .setText(String.format("#%03d", pokemonUrls.indexOf(pokemonUrl) + 1));
        holder.itemPokemonBinding.tvName.setText(pokemonUrl.getName());

        holder.itemPokemonBinding.clItem.setOnClickListener(v -> {
            onPokemonItemClickListener.onPokemonItemClick(pokemonUrl);
        });

        holder.itemPokemonBinding.ivFavourite.setOnClickListener(v -> {
            onPokemonItemClickListener.onFavouriteClick(pokemonUrl);
        });
    }

    @Override
    public int getItemCount() {
        return displayPokemonUrls.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ItemPokemonBinding itemPokemonBinding;

        public ViewHolder(@NonNull View view) {
            super(view);
            itemPokemonBinding = ItemPokemonBinding.bind(view);
        }
    }
}
