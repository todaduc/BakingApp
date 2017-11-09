package com.todaduc.bakingapp.tasks;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.BakingStep;
import com.todaduc.bakingapp.entities.Ingredient;
import com.todaduc.bakingapp.entities.Recipe;
import com.todaduc.bakingapp.ui.adapters.RecipeListAdapter;
import com.todaduc.bakingapp.utilities.JsonUtils;
import com.todaduc.bakingapp.utilities.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ddjankou on 8/31/2017.
 */

public class RecipeTask extends AsyncTask<Void, Void, List<Recipe>>{

    private final Activity appCompatActivity;
    private RecipeListAdapter recipeListAdapter;
    private List<Recipe> recipes;

    public RecipeTask(Activity appCompatActivity, RecipeListAdapter recipeListAdapter) {
        this.appCompatActivity = appCompatActivity;
        this.recipeListAdapter = recipeListAdapter;

    }


    @Override
    protected void onPreExecute() {
        ConnectivityManager cm =
                (ConnectivityManager) appCompatActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)){
            cancel(true);
        }
    }

    @Override
    protected List<Recipe> doInBackground(Void... params) {
      /* try {

            URL url = NetworkUtils.buildSimpleUrl(appCompatActivity.getBaseContext().getString(R.string.webservice_Request_Url));
            String queryResult = NetworkUtils.getResponseFromHttpUrl(url);

            this.recipes = JsonUtils.getRecipeFromJson(queryResult,appCompatActivity.getBaseContext());

        } catch (IOException | JSONException e) {

           e.printStackTrace();
        }*/

        return recipes;
    }

    @Override
    protected void onPostExecute(List<Recipe> recipes) {

        if(recipes!= null && !recipes.isEmpty()){
            recipeListAdapter.setRecipes(recipes);

        }else {
            recipeListAdapter.setRecipes(new ArrayList<Recipe>());

        }

        recipeListAdapter.notifyDataSetChanged();
    }

    public List<Recipe> getRecipes(){
        return recipes;
    }

    List< Ingredient > ingredientList;
    List< BakingStep > backingSteps;
    {
        ingredientList = new ArrayList<>();
        backingSteps = new ArrayList<>();
        recipes = new ArrayList<>();

        ingredientList.add(new Ingredient("2", "Cup", "Graham Cracker crumbs"));
        ingredientList.add(new Ingredient("2", "Cup", "Graham Cracker crumbs"));
        ingredientList.add(new Ingredient("2", "Cup", "Graham Cracker crumbs"));


        backingSteps.add(new BakingStep(0,"Recipe Introduction" ,"Recipe Introduction adfasdfasdfasdfasdfasdfasd",
                "", ""));
        backingSteps.add(new BakingStep(0,"Recipe Introduction" ,"Recipe Introduction",
                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4", ""));
        backingSteps.add(new BakingStep(0,"Recipe Introduction" ,"Recipe Introduction",
                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4", ""));
        backingSteps.add(new BakingStep(0,"Recipe Introduction" ,"Recipe Introduction adfasdfasdfasdfasdfasdfasd",
                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4", ""));
        backingSteps.add(new BakingStep(0,"Recipe Introduction" ,"Recipe Introduction adfasdfasdfasdfasdfasdfasd",
                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4", ""));
        backingSteps.add(new BakingStep(0,"Recipe Introduction" ,"Recipe Introduction adfasdfasdfasdfasdfasdfasd",
                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4", ""));
        backingSteps.add(new BakingStep(0,"Recipe Introduction" ,"Recipe Introduction adfasdfasdfasdfasdfasdfasd",
                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4", ""));
        backingSteps.add(new BakingStep(0,"Recipe Introduction" ,"Recipe Introduction  with diefferent size " +
                " of description text just to fixe recycler view bug adfasdfasdfasdfasdfasdfasd",
                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4", ""));
        backingSteps.add(new BakingStep(0,"Recipe Introduction" ,"Recipe Introduction adfasdfasdfasdfasdfasdfasd",
                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4", ""));
        backingSteps.add(new BakingStep(0,"Recipe Introduction" ,"Recipe Introduction adfasdfasdfasdfasdfasdfasd",
                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4", ""));
        backingSteps.add(new BakingStep(0,"Recipe Introduction" ,"Recipe Introduction  with diefferent size " +
                " of description text just to fixe recycler view bug adfasdfasdfasdfasdfasdfasd",
                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4", ""));
        backingSteps.add(new BakingStep(0,"Recipe Introduction" ,"Recipe Introduction adfasdfasdfasdfasdfasdfasd",
                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4", ""));
        backingSteps.add(new BakingStep(0,"Recipe Introduction" ,"Recipe Introduction adfasdfasdfasdfasdfasdfasd",
                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4", ""));
        backingSteps.add(new BakingStep(0,"Recipe Introduction" ,"Recipe Introduction  with diefferent size " +
                " of description text just to fixe recycler view bug adfasdfasdfasdfasdfasdfasd",
                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4", ""));
        backingSteps.add(new BakingStep(0,"Recipe Introduction" ,"Recipe Introduction adfasdfasdfasdfasdfasdfasd",
                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4", ""));

        recipes.add(new Recipe(1, "Nutella Pie", 2, "", ingredientList, backingSteps));
        recipes.add(new Recipe(2, "Brownies", 3, "", ingredientList, backingSteps) );
        recipes.add(new Recipe(3, "Yellow Cake", 4, "", ingredientList, backingSteps) );
        recipes.add(new Recipe(4, "Cheesecake", 5, "",  ingredientList, backingSteps) );
    }
}
