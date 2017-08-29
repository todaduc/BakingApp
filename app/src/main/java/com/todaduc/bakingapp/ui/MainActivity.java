package com.todaduc.bakingapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.todaduc.bakingapp.R;

public class MainActivity extends AppCompatActivity implements RecipeListFragment.OnRecipeClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onCardSelected(int position) {
       //need to pass value of recipe
        startActivity( new Intent(this, RecipeDetailActivity.class));
    }
}
