package com.todaduc.bakingapp.utilities;

import android.content.Context;
import com.todaduc.bakingapp.entities.BakingStep;
import com.todaduc.bakingapp.entities.Ingredient;
import com.todaduc.bakingapp.entities.Recipe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Themener on 8/22/17.
 */

public final class JsonUtils {

    public static List<Recipe> getRecipeFromJson(String recipeJsonStr, Context context)
            throws JSONException {

        List<Recipe> recipeList = new ArrayList<>();

        JSONArray recipeJsonArray= new JSONArray(recipeJsonStr);

        for (int i = 0; i< recipeJsonArray.length(); i++){

            JSONObject recipe = recipeJsonArray.getJSONObject(i);
            recipeList.add(new Recipe(
                    recipe.getInt("id"),
                    recipe.getString("name"),
                    recipe.getInt("servings"),
                    recipe.getString("image"),
                    getIngredientsFromJson(recipe.getJSONArray("ingredients"), context),
                    getStepsFromJson(recipe.getJSONArray("steps"), context)

            ));
        }
        return recipeList;
    }

    private static List<Ingredient> getIngredientsFromJson(JSONArray ingredientArray, Context context)throws JSONException{

        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < ingredientArray.length(); i++){

            JSONObject ingredient = ingredientArray.getJSONObject(i);

            ingredients.add(new Ingredient(
                    ingredient.getString("quantity"),
                    ingredient.getString("measure"),
                    ingredient.getString("ingredient")
            ));
        }
        return ingredients;
    }

    private static List<BakingStep> getStepsFromJson(JSONArray stepsArray, Context context)throws JSONException{

        List<BakingStep> bakingSteps = new ArrayList<>();
        for (int i = 0; i < stepsArray.length(); i++){

            JSONObject bakingStep = stepsArray.getJSONObject(i);
            bakingSteps.add(new BakingStep(
                    bakingStep.getInt("id"),
                    bakingStep.getString("shortDescription"),
                    bakingStep.getString("description"),
                    bakingStep.getString("videoURL"),
                    bakingStep.getString("thumbnailURL")
            ));
        }
        return bakingSteps;
    }
}
