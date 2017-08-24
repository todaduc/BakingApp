package com.todaduc.bakingapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.BakingStep;
import com.todaduc.bakingapp.entities.Ingredient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ddjankou on 8/24/2017.
 */

public class RecipeStepsFragment extends Fragment {
    List<Ingredient> ingredients = new ArrayList<>();
    List<BakingStep> backingSteps = new ArrayList<>();

    {
        ingredients.add( new Ingredient("500","G","Mascapone Cheese(room temperature)"));
        ingredients.add( new Ingredient("500","G","Mascapone Cheese(room temperature)"));
        ingredients.add( new Ingredient("500","G","Mascapone Cheese(room temperature)"));
        ingredients.add( new Ingredient("500","G","Mascapone Cheese(room temperature)"));
        ingredients.add( new Ingredient("500","G","Mascapone Cheese(room temperature)"));
        ingredients.add( new Ingredient("500","G","Mascapone Cheese(room temperature)"));
        backingSteps.add(new BakingStep(1,"a little test ", "more test","",""));
        backingSteps.add(new BakingStep(2,"a little test ", "more test","",""));
        backingSteps.add(new BakingStep(3,"a little test ", "more test","",""));
        backingSteps.add(new BakingStep(4,"a little test ", "more test","",""));
        backingSteps.add(new BakingStep(5,"a little test ", "more test","",""));

    }

    public RecipeStepsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_recipe_steps,container, false);

       // GridView gridView = (GridView)rootView.findViewById(R.id.recipe_steps_list);

        return rootView;
    }
}
