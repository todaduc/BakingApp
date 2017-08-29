package com.todaduc.bakingapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    private OnRecipeClickListener onRecipeClick;

    interface OnRecipeClickListener{
        void onCardSelected(int position);
    }


    {
        listRecipe.add(new Recipe(1,"one", 7, null));
        listRecipe.add(new Recipe(1,"two", 10, null));
        listRecipe.add(new Recipe(1,"three", 34, null));
        listRecipe.add(new Recipe(1,"four", 90, null));
        listRecipe.add(new Recipe(1,"two", 10, null));
        listRecipe.add(new Recipe(1,"three", 34, null));
        listRecipe.add(new Recipe(1,"four", 90, null));
    }


    public RecipeListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_recipe_list,container, false);

        GridView gridView = (GridView)rootView.findViewById(R.id.recipe_grid_view);

        RecipeListAdapter recipeListAdapter = new RecipeListAdapter(getContext(), listRecipe );

        gridView.setAdapter(recipeListAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onRecipeClick.onCardSelected(position);
            }
        });

        return  rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            onRecipeClick = (OnRecipeClickListener)context;
        }catch (ClassCastException e){
            throw  new ClassCastException(context.toString()+ " must implement OnRecipeClickListener");
        }

    }

}
