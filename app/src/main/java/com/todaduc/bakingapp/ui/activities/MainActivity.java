package com.todaduc.bakingapp.ui.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.Recipe;
import com.todaduc.bakingapp.tasks.RecipeTask;
import com.todaduc.bakingapp.ui.adapters.RecipeListAdapter;
import com.todaduc.bakingapp.utilities.RecipeRequestDelayer;
import com.todaduc.bakingapp.utilities.SimpleIdlingResource;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;


/*
 *   This class represents the main activity of the application.
 */
public class MainActivity extends AppCompatActivity implements RecipeRequestDelayer.DelayerCallBack, RecipeListAdapter.OnRecipeClickListener  {


    private RecipeListAdapter recipeListAdapter;
    private SimpleIdlingResource simpleIdlingResource;
    private GridLayoutManager gridLayoutManager;
    private static final int NUM_LAYOUT_COLUMNS = 1;
    private static final int NUM_LAYOUT_COLUMNS_TABLET = 3;

    @BindView(R.id.recipe_grid_view)
    RecyclerView recipeRecyclerView;


    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (simpleIdlingResource == null) {
            simpleIdlingResource = new SimpleIdlingResource();
        }
        return simpleIdlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTitle(R.string.activity_label);
        gridLayoutManager = new GridLayoutManager(this, getResources().getBoolean(R.bool.isTablet)?NUM_LAYOUT_COLUMNS_TABLET:NUM_LAYOUT_COLUMNS);

        getIdlingResource();
        recipeListAdapter = new RecipeListAdapter(this, new ArrayList<Recipe>(), this);
        RecipeRequestDelayer.processMessage(new RecipeTask(this, recipeListAdapter),this, simpleIdlingResource);
    }


    @Override
    public void onDone() {

        recipeRecyclerView.setLayoutManager(gridLayoutManager);
        recipeRecyclerView.setHasFixedSize(true);
        recipeRecyclerView.setAdapter(recipeListAdapter);
    }


    @Override
    public void onRecipeClick(Recipe recipe) {
        Intent intent = new Intent(MainActivity.this, RecipeDetailActivity.class);
        intent.putExtra(getString(R.string.activity_selected_recipe), recipe);
        startActivity(intent);
    }
}
