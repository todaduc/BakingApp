package com.todaduc.bakingapp.utilities;

import android.content.Context;

import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.BakingStep;
import com.todaduc.bakingapp.entities.Ingredient;
import com.todaduc.bakingapp.entities.Recipe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * This is an utility class to handle the recipe JSON data.
 */
public final class JsonUtils {


    /**
     * This method parses JSON from a web response and returns an array of recipe object.
     * @param recipeJsonStr JSON response from the server
     * @param context application context
     * @return List of recipe objects
     * @throws JSONException if JSON data cannot be properly parsed.
     */
    public static List<Recipe> getRecipeFromJson(String recipeJsonStr, Context context)
            throws JSONException {

        List<Recipe> recipeList = new ArrayList<>();

        JSONArray recipeJsonArray= new JSONArray(recipeJsonStr);

        for (int i = 0; i< recipeJsonArray.length(); i++){

            JSONObject recipe = recipeJsonArray.getJSONObject(i);
            recipeList.add(new Recipe(
                    recipe.getInt(context.getString(R.string.id)),
                    recipe.getString(context.getString(R.string.recipe_name)),
                    recipe.getInt(context.getString(R.string.recipe_serving)),
                    recipe.getString(context.getString(R.string.image)),
                    getIngredientsFromJson(recipe.getJSONArray(context.getString(R.string.recipe_ingredients)), context),
                    getStepsFromJson(recipe.getJSONArray(context.getString(R.string.recipe_steps)), context)

            ));
        }
        return recipeList;
    }

    /**
     * Help method to parse an ingredient list from a JSONArray object.
     * @param ingredientArray the JSONArray object
     * @param context the application context
     * @return list of ingredient objects
     * @throws JSONException if JSON data cannot be properly parsed.
     */
    private static List<Ingredient> getIngredientsFromJson(JSONArray ingredientArray, Context context)throws JSONException{

        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < ingredientArray.length(); i++){

            JSONObject ingredient = ingredientArray.getJSONObject(i);

            ingredients.add(new Ingredient(
                    ingredient.getString(context.getString(R.string.quantity)),
                    ingredient.getString(context.getString(R.string.measure)),
                    ingredient.getString(context.getString(R.string.ingredient))
            ));
        }
        return ingredients;
    }

    /**
     * Help method to parse a baking step list from a JSONArray object.
     * @param stepsArray the JSONArray object
     * @param context the application context
     * @return list of baking step objects
     * @throws JSONException if JSON data cannot be properly parsed.
     */

    private static List<BakingStep> getStepsFromJson(JSONArray stepsArray, Context context)throws JSONException{

        List<BakingStep> bakingSteps = new ArrayList<>();
        for (int i = 0; i < stepsArray.length(); i++){

            JSONObject bakingStep = stepsArray.getJSONObject(i);
            bakingSteps.add(new BakingStep(
                    bakingStep.getInt(context.getString(R.string.id)),
                    bakingStep.getString(context.getString(R.string.shortDescription)),
                    bakingStep.getString(context.getString(R.string.description)),
                    bakingStep.getString(context.getString(R.string.video_url)),
                    bakingStep.getString(context.getString(R.string.thumbnail_url))
            ));
        }
        return bakingSteps;
    }
}
