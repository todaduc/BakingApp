package com.todaduc.bakingapp.ui.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.BakingStep;
import com.todaduc.bakingapp.entities.Ingredient;
import com.todaduc.bakingapp.entities.Recipe;
import com.todaduc.bakingapp.ui.fragments.IngredientFragment;
import com.todaduc.bakingapp.ui.fragments.MediaPlayerFragment;
import com.todaduc.bakingapp.ui.fragments.StepListFragment;
import com.todaduc.bakingapp.ui.fragments.StepsDetailFragment;
import java.util.ArrayList;


public class RecipeDetailActivity extends AppCompatActivity implements StepListFragment.OnStepClickListener {

    boolean twoPaneMode;
    private Recipe mCurrentRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe_detail);


        Intent intent = getIntent();
        if(intent.hasExtra("Recipe")){
             mCurrentRecipe = intent.getExtras().getParcelable("Recipe");
             setTitle(mCurrentRecipe.getName());

            if(savedInstanceState == null){
                savedInstanceState = new Bundle();
            }

            savedInstanceState.putParcelableArrayList("RecipeSteps", (ArrayList<BakingStep>)mCurrentRecipe.getBackingSteps());
            savedInstanceState.putParcelableArrayList("RecipeIngredient", (ArrayList<Ingredient>)mCurrentRecipe.getIngredientList());



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

            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            MediaPlayerFragment mediaPlayerFragment = new MediaPlayerFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.video_player_container, mediaPlayerFragment)
                    .commit();
            StepsDetailFragment stepsDetailFragment = new StepsDetailFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.step_instruction_container, stepsDetailFragment)
                    .commit();
        }else{
            twoPaneMode = false;
        }
    }

    @Override
    public void onStepSelected(BakingStep currentStep) {
        if(!twoPaneMode){
            Intent intent = new Intent(this, StepsDetailActivity.class);
            intent.putExtra("RecipeName", mCurrentRecipe.getName());
            intent.putExtra("CurrentStep",currentStep);
            intent.putParcelableArrayListExtra("AllSteps", (ArrayList<BakingStep>)mCurrentRecipe.getBackingSteps() );
            startActivity(intent);
        }else{

            Bundle  savedInstanceState = new Bundle();
            savedInstanceState.putString("Video",currentStep.getVideoUrl().isEmpty()?currentStep.getThumbnailURL():currentStep.getVideoUrl());
            savedInstanceState.putString("Description",currentStep.getDescription());



            MediaPlayerFragment mediaPlayerFragment = new MediaPlayerFragment();
            mediaPlayerFragment.setArguments(savedInstanceState);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.video_player_container, mediaPlayerFragment)
                    .commit();

            StepsDetailFragment stepsDetailFragment = new StepsDetailFragment();
            stepsDetailFragment.setArguments(savedInstanceState);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.step_instruction_container, stepsDetailFragment)
                    .commit();
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
