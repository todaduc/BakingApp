package com.todaduc.bakingapp.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.todaduc.bakingapp.R;

/**
 * Created by ddjankou on 8/24/2017.
 */

public class StepsDetailActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_detail);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.video_player_container, new MediaPlayerFragment())
                .add(R.id.step_instruction_container, new StepsDetailFragment())
                .commit();
    }

}
