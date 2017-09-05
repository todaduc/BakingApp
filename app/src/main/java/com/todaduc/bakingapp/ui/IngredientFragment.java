package com.todaduc.bakingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.Ingredient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ddjankou on 8/30/2017.
 */

public class IngredientFragment extends Fragment {

    private RecyclerView mIngredient;
    List<Ingredient> ingredients = new ArrayList<>();

    public IngredientFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_ingredients, container, false);

        mIngredient = (RecyclerView)rootView.findViewById(R.id.rv_ingredient);
        LinearLayoutManager ingredientLayoutManager = new LinearLayoutManager(getContext());
        mIngredient.setLayoutManager(ingredientLayoutManager);
        mIngredient.setHasFixedSize(true);

        ingredients = this.getArguments().getParcelableArrayList("RecipeIngredient");


        IngredientAdapter ingredientAdapter = new IngredientAdapter(ingredients);
        mIngredient.setAdapter(ingredientAdapter);
        ingredientAdapter.notifyDataSetChanged();

        return rootView;
    }

}
