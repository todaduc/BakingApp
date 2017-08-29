package com.todaduc.bakingapp.ui;

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

    private RecyclerView mIngredient;
    List<Ingredient> ingredients = new ArrayList<>();

    {
        ingredients.add( new Ingredient("500","G","Mascapone Cheese(room temperature)"));
        ingredients.add( new Ingredient("500","G","Mascapone Cheese(room temperature)"));
        ingredients.add( new Ingredient("500","G","Mascapone Cheese(room temperature)"));
        ingredients.add( new Ingredient("500","G","Mascapone Cheese(room temperature)"));
        ingredients.add( new Ingredient("500","G","Mascapone Cheese(room temperature)"));
        ingredients.add( new Ingredient("500","G","Mascapone Cheese(room temperature)"));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        mIngredient = (RecyclerView)findViewById(R.id.rv_ingredient);
        LinearLayoutManager ingredientLayoutManager = new LinearLayoutManager(this);
        mIngredient.setLayoutManager(ingredientLayoutManager);
        mIngredient.setHasFixedSize(true);
        IngredientAdapter ingredientAdapter = new IngredientAdapter(ingredients);
        mIngredient.setAdapter(ingredientAdapter);
    }

    @Override
    public void onStepSelected(int position) {
        Toast.makeText(RecipeDetailActivity.this,"position clicked" + position,Toast.LENGTH_LONG).show();
    }
}
