package com.todaduc.bakingapp.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.Recipe;
import com.todaduc.bakingapp.tasks.RecipeTask;
import com.todaduc.bakingapp.ui.adapters.RecipeListAdapter;
import com.todaduc.bakingapp.ui.fragments.RecipeListFragment;
import com.todaduc.bakingapp.utilities.RecipeRequestDelayer;
import com.todaduc.bakingapp.utilities.SimpleIdlingResource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RecipeListAdapter.OnRecipeClickListener, RecipeRequestDelayer.DelayerCallBack  {


    boolean mTabletView;
    private RecipeListAdapter.OnRecipeClickListener onRecipeClick;
    private RecipeListAdapter recipeListAdapter;
    private SimpleIdlingResource simpleIdlingResource;

    @BindView(R.id.recipe_grid_view)
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTitle(R.string.activity_label);

        recipeListAdapter = new RecipeListAdapter(this, new ArrayList<Recipe>(), onRecipeClick);
        fetchRecipe();

        gridView.setAdapter(recipeListAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onRecipeClick.onCardSelected(recipeListAdapter.getRecipes().get(position));
            }
        });

    }

    private void fetchRecipe(){

        RecipeRequestDelayer.processMessage(new RecipeTask(this, recipeListAdapter),this, simpleIdlingResource);

    }

    @Override
    public void onDone(RecipeTask request) {
        return;
    }

    @Override
    public void onCardSelected(Recipe recipe) {
        Intent intent = new Intent(this, RecipeDetailActivity.class);
        intent.putExtra("Recipe", recipe);
        startActivity(intent);
    }

    public SimpleIdlingResource getSimpleIdlingResource(){
        return simpleIdlingResource;
    }

}
