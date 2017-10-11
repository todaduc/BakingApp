package com.todaduc.bakingapp.ui.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RecipeListAdapter.OnRecipeClickListener, RecipeRequestDelayer.DelayerCallBack  {


    boolean mTabletView;
    private RecipeListAdapter.OnRecipeClickListener onRecipeClick;
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

        recipeListAdapter = new RecipeListAdapter(this, new ArrayList<Recipe>(), onRecipeClick);
        getIdlingResource();

    }

    @Override
    protected void onStart() {
        super.onStart();
        RecipeRequestDelayer.processMessage(new RecipeTask(this, recipeListAdapter),this, simpleIdlingResource);
    }


    @Override
    public void onDone(List<Recipe> recipeList) {

        if(recipeList!= null && !recipeList.isEmpty()){
            recipeListAdapter.setRecipes(recipeList);

        }else {
            recipeListAdapter.setRecipes(new ArrayList<Recipe>());

        }
        recipeListAdapter.notifyDataSetChanged();
        gridView.setAdapter(recipeListAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //onRecipeClick.onCardSelected(recipeListAdapter.getRecipes().get(position));

                Recipe recipe = (Recipe)parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, RecipeDetailActivity.class);
                intent.putExtra("Recipe", recipe);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onCardSelected(Recipe recipe) {

    }

    public SimpleIdlingResource getSimpleIdlingResource(){
        return simpleIdlingResource;
    }

}
