package com.todaduc.bakingapp.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.Recipe;
import com.todaduc.bakingapp.ui.fragments.RecipeListFragment;
import com.todaduc.bakingapp.utilities.SimpleIdlingResource;

public class MainActivity extends AppCompatActivity implements RecipeListFragment.OnRecipeClickListener {


    boolean mTabletView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.activity_label);
    }


    @Override
    public void onCardSelected(Recipe recipe) {
       //need to pass value of recipe
        Intent intent = new Intent(this, RecipeDetailActivity.class);
        intent.putExtra("Recipe", recipe);
        startActivity(intent);
    }

}
