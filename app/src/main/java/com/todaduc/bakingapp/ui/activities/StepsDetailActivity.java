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

/**
 * Created by ddjankou on 8/24/2017.
 */

public class StepsDetailActivity  extends AppCompatActivity {

    @BindView(R.id.button_preview)
    Button mPreview;
    @BindView(R.id.button_next)
    Button mNext;
    private BakingStep bakingStep;
    private static List<BakingStep> mListOfSteps;
    private static String recipeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_detail);
        ButterKnife.bind(this);

        if(getIntent().hasExtra("RecipeName")){
            setTitle(getIntent().getExtras().get("RecipeName").toString());

        }
        if(getIntent().hasExtra("AllSteps")){
            mListOfSteps = getIntent().getExtras().getParcelableArrayList("AllSteps");
        }
        if(getIntent().hasExtra("CurrentStep")){

            bakingStep = getIntent().getExtras().getParcelable("CurrentStep");
            if(savedInstanceState==null){
                savedInstanceState = new Bundle();

            }
            savedInstanceState.putString("Video",bakingStep.getVideoUrl());
            savedInstanceState.putString("Description",bakingStep.getDescription());

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

        if(getIntent().hasExtra("CurrentStep")){
            bakingStep = getIntent().getExtras().getParcelable("CurrentStep");
            outState.putString("Video",bakingStep.getVideoUrl());
            outState.putString("Description",bakingStep.getDescription());

        }
        if(getIntent().hasExtra("AllSteps")){
            mListOfSteps = getIntent().getExtras().getParcelableArrayList("AllSteps");
            outState.putParcelableArrayList("AllSteps", (ArrayList<BakingStep>) mListOfSteps);
        }
        super.onSaveInstanceState(outState);
    }

    @OnClick(R.id.button_preview)
    public void previewStep(){
        int currentStepIndex = 0;

        if(mListOfSteps !=null){
            currentStepIndex = mListOfSteps.indexOf(bakingStep);
            if(currentStepIndex>0){
                BakingStep previewStep = mListOfSteps.get(--currentStepIndex);
                refreshActivity( previewStep);
            }else {
                Toast.makeText(this, "No more Steps", Toast.LENGTH_LONG).show();
            }
        }
        return;
    }

    @OnClick(R.id.button_next)
    public void nextStep(){
        int currentStepIndex = 0;

        if(mListOfSteps !=null){
            currentStepIndex = mListOfSteps.indexOf(bakingStep);
            if(currentStepIndex<mListOfSteps.size()-1){
                BakingStep nextStep = mListOfSteps.get(++currentStepIndex);
                refreshActivity(nextStep);
            }else{
                Toast.makeText(this, "No more Steps", Toast.LENGTH_LONG).show();
            }
        }
        return;
    }

    private void refreshActivity(BakingStep bakingStep){

        String recipeName = getIntent().getExtras().get("RecipeName").toString();

        Intent intent = new Intent(this, StepsDetailActivity.class);
        intent.putExtra("RecipeName", recipeName);

        intent.putExtra("CurrentStep",bakingStep);
        intent.putParcelableArrayListExtra("AllSteps", (ArrayList<BakingStep>) mListOfSteps);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}
