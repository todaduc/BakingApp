package com.todaduc.bakingapp.tasks;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.Recipe;
import com.todaduc.bakingapp.ui.RecipeListAdapter;
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

    private final AppCompatActivity appCompatActivity;
    private RecipeListAdapter recipeListAdapter;
    private List<Recipe> recipes;

    public RecipeTask(AppCompatActivity appCompatActivity, RecipeListAdapter recipeListAdapter) {
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
        try {
            URL url = NetworkUtils.buildSimpleUrl(appCompatActivity.getBaseContext().getString(R.string.webservice_Request_Url));
            String queryResult =NetworkUtils.getResponseFromHttpUrl(url);

            recipes = JsonUtils.getRecipeFromJson(queryResult,appCompatActivity.getBaseContext());

        } catch (IOException | JSONException e) {

           e.printStackTrace();
        }

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

}
