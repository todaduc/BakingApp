package com.todaduc.bakingapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;
import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.BakingStep;
import com.todaduc.bakingapp.ui.fragments.MediaPlayerFragment;
import com.todaduc.bakingapp.ui.fragments.StepsDetailFragment;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/*
 * This activity Class in charge of displaying details steps of a recipe.
 */
public class StepsDetailActivity  extends AppCompatActivity {

    @BindView(R.id.button_preview)
    Button mPreview;
    @BindView(R.id.button_next)
    Button mNext;
    private BakingStep bakingStep;
    private static List<BakingStep> mListOfSteps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_detail);
        ButterKnife.bind(this);

        if(getIntent().hasExtra(getString(R.string.activity_selected_recipe_name))){
            setTitle(getIntent().getExtras().get(getString(R.string.activity_selected_recipe_name)).toString());

        }
        if(getIntent().hasExtra(getString(R.string.activity_recipe_all_steps))){
            mListOfSteps = getIntent().getExtras().getParcelableArrayList(getString(R.string.activity_recipe_all_steps));
        }
        if(getIntent().hasExtra(getString(R.string.activity_recipe_selected_step))){

            bakingStep = getIntent().getExtras().getParcelable(getString(R.string.activity_recipe_selected_step));
            if(savedInstanceState==null){
                savedInstanceState = new Bundle();

            }

            savedInstanceState.putString(getString(R.string.activity_selected_recipe_video),bakingStep.getVideoUrl().isEmpty()?bakingStep.getThumbnailURL():bakingStep.getVideoUrl());
            savedInstanceState.putString(getString(R.string.activity_selected_recipe_desc),bakingStep.getDescription());

        }

        MediaPlayerFragment mediaPlayerFragment = new MediaPlayerFragment();
        mediaPlayerFragment.setArguments(savedInstanceState);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.video_player_container, mediaPlayerFragment)
                .commit();

        StepsDetailFragment stepsDetailFragment = new StepsDetailFragment();
        stepsDetailFragment.setArguments(savedInstanceState);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.step_instruction_container, stepsDetailFragment)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        if(getIntent().hasExtra(getString(R.string.activity_recipe_selected_step))){
            bakingStep = getIntent().getExtras().getParcelable(getString(R.string.activity_recipe_selected_step));
            outState.putString(getString(R.string.activity_selected_recipe_video),bakingStep.getVideoUrl().isEmpty()?bakingStep.getThumbnailURL():bakingStep.getVideoUrl());
            outState.putString(getString(R.string.activity_selected_recipe_desc),bakingStep.getDescription());

        }
        if(getIntent().hasExtra(getString(R.string.activity_recipe_all_steps))){
            mListOfSteps = getIntent().getExtras().getParcelableArrayList(getString(R.string.activity_recipe_all_steps));
            outState.putParcelableArrayList(getString(R.string.activity_recipe_all_steps), (ArrayList<BakingStep>) mListOfSteps);
        }
        super.onSaveInstanceState(outState);
    }

    /**
     * This Method handles the browsing to the preview baking step when the user's device is a phone.
     */
    @OnClick(R.id.button_preview)
    public void previewStep(){
        int currentStepIndex;

        if(mListOfSteps !=null){
            currentStepIndex = mListOfSteps.indexOf(bakingStep);
            if(currentStepIndex>0){
                BakingStep previewStep = mListOfSteps.get(--currentStepIndex);
                refreshActivity( previewStep);
            }else {
                Toast.makeText(this, R.string.activity_no_more_step_message, Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * This Method handles the browsing to the next baking step when the user's device is a phone.
     */
    @OnClick(R.id.button_next)
    public void nextStep(){
        int currentStepIndex;

        if(mListOfSteps !=null){
            currentStepIndex = mListOfSteps.indexOf(bakingStep);
            if(currentStepIndex<mListOfSteps.size()-1){
                BakingStep nextStep = mListOfSteps.get(++currentStepIndex);
                refreshActivity(nextStep);
            }else{
                Toast.makeText(this, R.string.activity_no_more_step_message, Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * Help method to refresh the current baking step on the activity.
     * @param bakingStep baking step
     */
    private void refreshActivity(BakingStep bakingStep){

        String recipeName = getIntent().getExtras().get(getString(R.string.activity_selected_recipe_name)).toString();

        Intent intent = new Intent(this, StepsDetailActivity.class);
        intent.putExtra(getString(R.string.activity_selected_recipe_name), recipeName);

        intent.putExtra(getString(R.string.activity_recipe_selected_step),bakingStep);
        intent.putParcelableArrayListExtra("AllSteps", (ArrayList<BakingStep>) mListOfSteps);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}
