package com.todaduc.bakingapp.ui.fragments;

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
import com.todaduc.bakingapp.tasks.RecipeTask;
import com.todaduc.bakingapp.ui.activities.MainActivity;
import com.todaduc.bakingapp.ui.adapters.RecipeListAdapter;
import com.todaduc.bakingapp.utilities.RecipeRequestDelayer;
import com.todaduc.bakingapp.utilities.SimpleIdlingResource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Themener on 8/23/17.
 */

public class RecipeListFragment extends Fragment implements RecipeRequestDelayer.DelayerCallBack {


    private OnRecipeClickListener onRecipeClick;
    private RecipeListAdapter recipeListAdapter;

    private SimpleIdlingResource mIdlingResource;

    @BindView(R.id.recipe_grid_view)
    GridView gridView;

    @Override
    public void onDone(String request) {

    }

    public interface OnRecipeClickListener{
        void onCardSelected(Recipe recipe);
    }

    public RecipeListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_recipe_list,container, false);

        ButterKnife.bind(this, rootView);

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
