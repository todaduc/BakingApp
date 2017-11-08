package com.todaduc.bakingapp.ui.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity implements RecipeRequestDelayer.DelayerCallBack  {


    private RecipeListAdapter recipeListAdapter;
    private SimpleIdlingResource simpleIdlingResource;

    @BindView(R.id.recipe_grid_view)
    GridView gridView;


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

        getIdlingResource();
        recipeListAdapter = new RecipeListAdapter(this, new ArrayList<Recipe>());
        RecipeRequestDelayer.processMessage(new RecipeTask(this, recipeListAdapter),this, simpleIdlingResource);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    @Override
    public void onDone() {


        gridView.setAdapter(recipeListAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Recipe recipe = recipeListAdapter.getRecipes().get(position);
                Intent intent = new Intent(MainActivity.this, RecipeDetailActivity.class);
                intent.putExtra(getString(R.string.activity_selected_recipe), recipe);
                startActivity(intent);
            }
        });

    }



    public SimpleIdlingResource getSimpleIdlingResource(){
        return simpleIdlingResource;
    }

}
