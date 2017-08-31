package com.todaduc.bakingapp.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.Ingredient;
import java.util.ArrayList;
import java.util.List;

public class RecipeDetailActivity extends AppCompatActivity implements StepListFragment.OnStepClickListener{

    boolean twoPaneMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

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
    public void onStepSelected(int position) {
        if(!twoPaneMode){
            Intent intent = new Intent(this, StepsDetailActivity.class);
            startActivity(intent);
        }else{
            return;
        }

    }
}
