package io.azet.pokemon.details.view;

import android.content.res.ColorStateList;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;

import java.util.List;

import io.azet.pokemon.common.Utils;
import io.azet.pokemon.common.svg.SvgSoftwareLayerSetter;
import io.azet.pokemon.details.presenter.PokemonDetailsPresenter;
import io.azet.pokemon.list.model.PokemonUrl;
import io.azet.pokemon.list.view.PokemonListActivity;
import io.azet.pokemonUrl.R;
import io.azet.pokemonUrl.databinding.ActivityDetailsBinding;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class PokemonDetailsActivity extends AppCompatActivity implements PokemonDetailsView{

    private PokemonDetailsPresenter pokePresenter;
    private ActivityDetailsBinding activityDetailsBinding;
    private RequestBuilder glideRequestBuilder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailsBinding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(activityDetailsBinding.getRoot());

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            Log.e("Pokemon", "Started PokemonDetailsActivity without extras!");
            finish();
            return;
        }

        glideRequestBuilder =
                Glide.with(this)
                        .as(PictureDrawable.class)
                        .error(R.drawable.ic_favourite_border)
                        .transition(withCrossFade())
                        .listener(new SvgSoftwareLayerSetter());

        PokemonUrl pokemonUrl = extras.getParcelable(PokemonListActivity.EXTRA_POKEMON_URL);
        pokePresenter = new PokemonDetailsPresenter(this, pokemonUrl);
        pokePresenter.fetchPokemonDetails();
    }

    @Override
    public void updatePokemonBase(boolean isFavourite, String name) {
        activityDetailsBinding.tvName.setText(name);

        activityDetailsBinding.ivFavourite.setImageResource(isFavourite ?
                R.drawable.ic_favourite : R.drawable.ic_favourite_border);
    }

    @Override
    public void updatePokemonDetails(int height, int weight, List<String> types) {
        String formattedWeight = getResources().getString(R.string.formatted_weight, weight);
        activityDetailsBinding.tvWeight.setText(formattedWeight);

        String formattedHeight = getResources().getString(R.string.formatted_height, height);
        activityDetailsBinding.tvHeight.setText(formattedHeight);

        types.forEach(this::addTypeChip);

    }

    private void addTypeChip(String type) {
        Chip typeChip = new Chip(this);
        typeChip.setText(type);
        int backgroundColor = Utils.getBackgroundColorByType(type);
        int fontColor = Utils.getFontColorByBackground(backgroundColor);
        ChipDrawable chipDrawable = (ChipDrawable) typeChip.getChipDrawable();
        chipDrawable.setChipBackgroundColor(ColorStateList.valueOf(backgroundColor));
        typeChip.setTextColor(fontColor);
        activityDetailsBinding.chgTypes.addView(typeChip);
    }

    @Override
    public void updatePokemonDescription(String description) {
        activityDetailsBinding.tvDescription.setText(description);
    }

    @Override
    public void updatePokemonSprite(String url) {
        Uri uri = Uri.parse(url);
        glideRequestBuilder.load(uri).into(activityDetailsBinding.ivSprite);
    }

}
