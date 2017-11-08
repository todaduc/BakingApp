package com.todaduc.bakingapp.ui.activities;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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

/**
 * Created by ddjankou on 10/20/2017.
 */

public class WidgetConfigurationActivity extends Activity  {

    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    private static final String PREFS_NAME = "AppWidget";
    private static final String PREF_PREFIX_KEY = "appwidget";
    private RecipeListAdapter recipeListAdapter;


    @BindView(R.id.recipe_grid_view)
    GridView gridView;

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
        // Set layout size of activity
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        recipeListAdapter = new RecipeListAdapter(this, new ArrayList<Recipe>());
        new RecipeTask(this, recipeListAdapter).execute();

        gridView.setAdapter(recipeListAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe recipe = recipeListAdapter.getRecipes().get(position);
                createWidget(getApplicationContext(), recipe);
            }
        });


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
            return;
        }


    }

    private void createWidget(Context context, Recipe prefRecipe) {
        // Store the string locally
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

    // Write the prefix to the SharedPreferences object for this widget
    public  void savePreferredRecipe(Context context, int appWidgetId, Recipe prefRecipe) {


        //DB call to save the preRecipe with Id
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

      // Read the prefix from the preferred recipe database for this widget.
    public static Recipe loadTitlePref(Context context, int appWidgetId) {

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

    public static void deleteTitlePref(Context context, int appWidgetId) {

      int id =   context.getContentResolver().delete(RecipeIngredientContract.FavoritesRecipeEntry.CONTENT_URI
                ,RecipeIngredientContract.FavoritesRecipeEntry.COLUMN_WIDGET_ID + " =?"
                ,new String[]{Integer.toString(appWidgetId)}
        );

    }


}

