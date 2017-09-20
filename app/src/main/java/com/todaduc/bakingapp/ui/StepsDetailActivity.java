package com.todaduc.bakingapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.BakingStep;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.id;

/**
 * Created by ddjankou on 8/24/2017.
 */

public class StepsDetailActivity  extends AppCompatActivity {

    @BindView(R.id.button_preview)
    Button mPreview;
    @BindView(R.id.button_next)
    Button mNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_detail);
        ButterKnife.bind(this);

        if(getIntent().hasExtra("BakingStep")){

            BakingStep bakingStep = getIntent().getExtras().getParcelable("BakingStep");
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

        if(getIntent().hasExtra("BakingStep")){
            BakingStep bakingStep = getIntent().getExtras().getParcelable("BakingStep");
            outState.putString("Video",bakingStep.getVideoUrl());
            outState.putString("Description",bakingStep.getDescription());

        }
        super.onSaveInstanceState(outState);
    }

    @OnClick(R.id.button_preview)
    public void previewStep(){

    }

    @OnClick(R.id.button_next)
    public void nextStep(){

    }
}
