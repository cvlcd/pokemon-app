package io.azet.pokemon.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import io.azet.pokemon.model.PokemonUrl;
import io.azet.pokemon.view.PokemonListAdapter.OnPokemonItemClickListener;
import io.azet.pokemonUrl.databinding.FragmentListBinding;

public class PokemonListFragment extends Fragment implements OnPokemonItemClickListener {

    public static final String KEY_POKEMON_URLS = "pokemon_urls";

    private FragmentListBinding fragmentListBinding;
    private PokemonListAdapter pokemonListAdapter;


    public static Fragment newInstance(ArrayList<PokemonUrl> pokemonUrls) {
        PokemonListFragment fragment = new PokemonListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("pokemon_urls", pokemonUrls);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();

        ArrayList<PokemonUrl> pokemonUrls = new ArrayList<>();
        if (bundle != null) {
            pokemonUrls = bundle.getParcelableArrayList(KEY_POKEMON_URLS);
        }

        fragmentListBinding = FragmentListBinding.inflate(inflater, container, false);
        setupPokemonListAdapter(pokemonUrls);

        return fragmentListBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentListBinding = null;
    }

    private void setupPokemonListAdapter(ArrayList<PokemonUrl> pokemonUrls) {
        pokemonListAdapter = new PokemonListAdapter(pokemonUrls, (OnPokemonItemClickListener) getActivity());
        fragmentListBinding.rvPokemons.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentListBinding.rvPokemons.setAdapter(pokemonListAdapter);

//        pokemonListAdapter.setOnItemClickListener(onItemClickListener);
    }

    @Override
    public void onPokemonItemClick(PokemonUrl item) {

    }

    @Override
    public void onFavouriteClick(PokemonUrl item) {

    }
}
