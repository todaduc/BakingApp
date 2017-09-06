package com.todaduc.bakingapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.Recipe;
import com.todaduc.bakingapp.tasks.RecipeTask;

import java.util.ArrayList;


/**
 * Created by Themener on 8/23/17.
 */

public class RecipeListFragment extends Fragment {


    private OnRecipeClickListener onRecipeClick;
    private RecipeListAdapter recipeListAdapter;

    interface OnRecipeClickListener{
        void onCardSelected(Recipe recipe);
    }

    public RecipeListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_recipe_list,container, false);

        GridView gridView = (GridView)rootView.findViewById(R.id.recipe_grid_view);

        recipeListAdapter = new RecipeListAdapter(getContext(), new ArrayList<Recipe> ());

        fetchRecipe();

        gridView.setAdapter(recipeListAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onRecipeClick.onCardSelected(recipeListAdapter.getRecipes().get(position));
            }
        });

        return  rootView;
    }

    private void fetchRecipe(){
        new RecipeTask((MainActivity)getActivity(), recipeListAdapter).execute();
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
