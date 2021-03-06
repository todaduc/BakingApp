package com.todaduc.bakingapp.ui.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.BakingStep;
import com.todaduc.bakingapp.entities.Ingredient;
import com.todaduc.bakingapp.entities.Recipe;
import com.todaduc.bakingapp.ui.fragments.IngredientFragment;
import com.todaduc.bakingapp.ui.fragments.MediaPlayerFragment;
import com.todaduc.bakingapp.ui.fragments.StepListFragment;
import com.todaduc.bakingapp.ui.fragments.StepsDetailFragment;
import java.util.ArrayList;
import java.util.List;

/*
 * This activity Class in charge of displaying recipe details.
 */
public class RecipeDetailActivity extends AppCompatActivity implements StepListFragment.OnStepClickListener {

    private Recipe mCurrentRecipe;
    private List<BakingStep> listOfSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_recipe_detail);


        Intent intent = getIntent();
        if(intent.hasExtra(getString(R.string.activity_selected_recipe))) {
            mCurrentRecipe = intent.getExtras().getParcelable(getString(R.string.activity_selected_recipe));
            setTitle(mCurrentRecipe.getName());
            listOfSteps = mCurrentRecipe.getBackingSteps();

            if(savedInstanceState == null){
                savedInstanceState = new Bundle();
                savedInstanceState.putParcelableArrayList(getString(R.string.activity_selected_recipe_steps), (ArrayList<BakingStep>)listOfSteps);
                savedInstanceState.putParcelableArrayList(getString(R.string.activity_selected_recipe_ingredient), (ArrayList<Ingredient>)mCurrentRecipe.getIngredientList());

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
        }

        //If the used device is a tablet.
        if(getResources().getBoolean(R.bool.isTablet)){

            MediaPlayerFragment mediaPlayerFragment = new MediaPlayerFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.video_player_container, mediaPlayerFragment)
                    .commit();
            StepsDetailFragment stepsDetailFragment = new StepsDetailFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.step_instruction_container, stepsDetailFragment)
                    .commit();
        }
    }

    /**
     * The method handles the click on a baking step. Sets appropriate fragments if the users device is a tablet.
     * @param currentStep current baking step
     */
    @Override
    public void onStepSelected(BakingStep currentStep) {
        if(!getResources().getBoolean(R.bool.isTablet)){
            Intent intent = new Intent(this, StepsDetailActivity.class);
            intent.putExtra(getString(R.string.activity_selected_recipe_name), mCurrentRecipe.getName());
            intent.putExtra(getString(R.string.activity_recipe_selected_step),currentStep);
            intent.putParcelableArrayListExtra(getString(R.string.activity_recipe_all_steps), (ArrayList<BakingStep>)mCurrentRecipe.getBackingSteps() );
            startActivity(intent);
        }else{

            Bundle  savedInstanceState = new Bundle();
            savedInstanceState.putString(getString(R.string.activity_selected_recipe_video),currentStep.getVideoUrl());
            savedInstanceState.putString(getString(R.string.activity_selected_recipe_desc),currentStep.getDescription());
            savedInstanceState.putString(getString(R.string.thumbnail_url),currentStep.getThumbnailURL());


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
       //shouldn't depend on the intent state, but actual list adapter state.
        if(outState != null){
            outState.putParcelableArrayList(getString(R.string.activity_selected_recipe_steps), (ArrayList<BakingStep>)mCurrentRecipe.getBackingSteps());
            outState.putParcelableArrayList(getString(R.string.activity_selected_recipe_ingredient), (ArrayList<Ingredient>)mCurrentRecipe.getIngredientList());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        listOfSteps = savedInstanceState.getParcelableArrayList(getString(R.string.activity_selected_recipe_steps));
    }

}
