package com.todaduc.bakingapp.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.BakingStep;
import com.todaduc.bakingapp.entities.Ingredient;
import com.todaduc.bakingapp.entities.Recipe;
import java.util.ArrayList;


public class RecipeDetailActivity extends AppCompatActivity implements StepListFragment.OnStepClickListener{

    boolean twoPaneMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);


        Intent intent = getIntent();
        if(intent.hasExtra("Recipe")){
            Recipe recipe = intent.getExtras().getParcelable("Recipe");

            if(savedInstanceState == null){
                savedInstanceState = new Bundle();
            }

            savedInstanceState.putParcelableArrayList("RecipeSteps", (ArrayList<BakingStep>)recipe.getBackingSteps());
            savedInstanceState.putParcelableArrayList("RecipeIngredient", (ArrayList<Ingredient>)recipe.getIngredientList());



            IngredientFragment ingredientFragment = new IngredientFragment();
            ingredientFragment.setArguments(savedInstanceState);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.recipe_ingredients_container, ingredientFragment)
                    .commit();

            StepListFragment stepListFragment = new StepListFragment();
            stepListFragment.setArguments(savedInstanceState);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.recipe_detail_container, stepListFragment)
                    .commit();
        }


        if(findViewById(R.id.tablet_linear_layout)!= null){
            twoPaneMode = true;

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.video_player_container, new MediaPlayerFragment())
                    .add(R.id.step_instruction_container, new StepsDetailFragment())
                    .commit();
        }else{
            twoPaneMode = false;
        }
    }

    @Override
    public void onStepSelected(BakingStep bakingStep) {
        if(!twoPaneMode){
            Intent intent = new Intent(this, StepsDetailActivity.class);
            intent.putExtra("BakingStep",bakingStep);
            startActivity(intent);
        }else{
            return;
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if(getIntent().hasExtra("Recipe")){
           Recipe recipe = getIntent().getExtras().getParcelable("Recipe");
        outState.putParcelableArrayList("RecipeSteps", (ArrayList<BakingStep>)recipe.getBackingSteps());
        outState.putParcelableArrayList("RecipeIngredient", (ArrayList<Ingredient>)recipe.getIngredientList());
        }
        super.onSaveInstanceState(outState);
    }
}
