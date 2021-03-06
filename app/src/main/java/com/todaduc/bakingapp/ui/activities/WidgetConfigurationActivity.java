package com.todaduc.bakingapp.ui.activities;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import com.todaduc.bakingapp.ui.widget.BakingWidget;
import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.data.RecipeIngredientContract;
import com.todaduc.bakingapp.entities.Ingredient;
import com.todaduc.bakingapp.entities.Recipe;
import com.todaduc.bakingapp.tasks.RecipeTask;
import com.todaduc.bakingapp.ui.adapters.RecipeListAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


/*
 * This activity helps users to pick the favorite recipe ingredients,
 * that should be displayed by the widget.
 */
public class WidgetConfigurationActivity extends Activity implements RecipeListAdapter.OnRecipeClickListener {

    private int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    private static final String PREFS_NAME = "AppWidget";
    private static final String PREF_PREFIX_KEY = "appwidget";
    private RecipeListAdapter recipeListAdapter;
    private LinearLayoutManager mLayoutManager;

    @BindView(R.id.recipe_grid_view)
    RecyclerView gridView;

    public WidgetConfigurationActivity() {
        super();
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED);

        setContentView(R.layout.activity_widget_configuration);
        ButterKnife.bind(this);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        mLayoutManager = new LinearLayoutManager(this);
        recipeListAdapter = new RecipeListAdapter(this, new ArrayList<Recipe>(), this);
        new RecipeTask(this, recipeListAdapter).execute();

        gridView.setLayoutManager(mLayoutManager);
        gridView.setHasFixedSize(true);
        gridView.setAdapter(recipeListAdapter);
       /* gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe recipe = recipeListAdapter.getRecipes().get(position);
                createWidget(getApplicationContext(), recipe);
            }
        });
*/

        // Find the widget id from the intent.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }


    }

    private void createWidget(Context context, Recipe prefRecipe) {
        // Store the recipe locally
        savePreferredRecipe(context, mAppWidgetId, prefRecipe);

        // It is the responsibility of the configuration activity to update the app widget
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        BakingWidget.updateAppWidget(context, appWidgetManager, mAppWidgetId);

        // Make sure we pass back the original appWidgetId
        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        setResult(RESULT_OK, resultValue);
        finish();
    }

    /**
     * Saves the preferred recipe in the local database for this widget.
     * @param context application context
     * @param appWidgetId widget identification number
     * @param prefRecipe preferred recipe object
     */
    public  void savePreferredRecipe(Context context, int appWidgetId, Recipe prefRecipe) {


        for (Ingredient ingredient: prefRecipe.getIngredientList() ){

            ContentValues contentValues = new ContentValues();
            contentValues.put(RecipeIngredientContract.FavoritesRecipeEntry.COLUMN_WIDGET_ID, appWidgetId);
            contentValues.put(RecipeIngredientContract.FavoritesRecipeEntry.COLUMN_INGREDIENT_DESC, ingredient.getDescription());
            contentValues.put(RecipeIngredientContract.FavoritesRecipeEntry.COLUMN_MEASURE, ingredient.getMeasure());
            contentValues.put(RecipeIngredientContract.FavoritesRecipeEntry.COLUMN_QUANTITY, ingredient.getQuantity());
            contentValues.put(RecipeIngredientContract.FavoritesRecipeEntry.COLUMN_RECIPE_NAME, prefRecipe.getName());

            context.getContentResolver().insert(RecipeIngredientContract.FavoritesRecipeEntry.CONTENT_URI, contentValues);
        }

    }

    /**
     *  Read the preferred recipe from local database for this widget.
     * @param context application context
     * @param appWidgetId widget identification number
     * @return the found recipe or a null object
     */
    public static Recipe loadPreferredRecipe(Context context, int appWidgetId) {

        Recipe foundRecipe = null;
        Cursor cursor = null;
        try{
            cursor = context.getContentResolver().query(RecipeIngredientContract.FavoritesRecipeEntry.CONTENT_URI,
                    null,
                    RecipeIngredientContract.FavoritesRecipeEntry.COLUMN_WIDGET_ID + " =?",
                    new String[]{Integer.toString(appWidgetId)},
                    null
                    );



        List<Ingredient> ingredientList = new ArrayList<>();

        if(cursor!= null){
            int nameIndex = cursor.getColumnIndex(RecipeIngredientContract.FavoritesRecipeEntry.COLUMN_RECIPE_NAME);
            int quantityIndex = cursor.getColumnIndex(RecipeIngredientContract.FavoritesRecipeEntry.COLUMN_QUANTITY);
            int measureIndex = cursor.getColumnIndex(RecipeIngredientContract.FavoritesRecipeEntry.COLUMN_MEASURE);
            int descriptionIndex = cursor.getColumnIndex(RecipeIngredientContract.FavoritesRecipeEntry.COLUMN_INGREDIENT_DESC);

            while (cursor.moveToNext()){

                ingredientList.add(new Ingredient(
                        cursor.getString(quantityIndex),
                        cursor.getString(measureIndex),
                        cursor.getString(descriptionIndex)));

                if(cursor.isLast()){
                    foundRecipe = new Recipe(0, cursor.getString(nameIndex), 0, null, ingredientList, null);

                }

            }
        }

        }finally {
            if(cursor != null)
                cursor.close();
        }
       return  foundRecipe;
    }

    /**
     * Method that delete the widget recipe from the database,
     * when this widget get remove from the application.
     * @param context application context
     * @param appWidgetId widget identification number
     */
    public static void deleteTitlePref(Context context, int appWidgetId) {

        context.getContentResolver().delete(RecipeIngredientContract.FavoritesRecipeEntry.CONTENT_URI
                ,RecipeIngredientContract.FavoritesRecipeEntry.COLUMN_WIDGET_ID + " =?"
                ,new String[]{Integer.toString(appWidgetId)}
        );

    }


    @Override
    public void onRecipeClick(Recipe recipe) {
        createWidget(getApplicationContext(), recipe);
    }
}

