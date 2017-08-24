package com.todaduc.bakingapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Themener on 8/23/17.
 */

public class RecipeListFragment extends Fragment {

    List<Recipe> listRecipe = new ArrayList<>();

    {
        listRecipe.add(new Recipe(1,"one"));
        listRecipe.add(new Recipe(1,"two"));
        listRecipe.add(new Recipe(1,"three"));
        listRecipe.add(new Recipe(1,"four"));
    }

    public RecipeListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_recipe_list,container, false);

        GridView gridView = (GridView)rootView.findViewById(R.id.recipe_grid_view);

        RecipeListAdapter recipeListAdapter = new RecipeListAdapter(getContext(), listRecipe);

        gridView.setAdapter(recipeListAdapter);
        return  rootView;
    }
}
