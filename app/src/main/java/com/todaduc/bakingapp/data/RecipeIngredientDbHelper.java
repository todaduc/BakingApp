package com.todaduc.bakingapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Database helper class.
 */
public class RecipeIngredientDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FavoriteRecipe.db";

    private static final  int   DATABASE_VERSION = 1;

    public RecipeIngredientDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_PREFERRED_RECIPE_TABLE = "CREATE TABLE "+
                RecipeIngredientContract.FavoritesRecipeEntry.TABLE_NAME + " ("+
                RecipeIngredientContract.FavoritesRecipeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                RecipeIngredientContract.FavoritesRecipeEntry.COLUMN_WIDGET_ID + " INTEGER NOT NULL," +
                RecipeIngredientContract.FavoritesRecipeEntry.COLUMN_RECIPE_NAME + " TEXT NOT NULL," +
                RecipeIngredientContract.FavoritesRecipeEntry.COLUMN_QUANTITY + " TEXT NOT NULL," +
                RecipeIngredientContract.FavoritesRecipeEntry.COLUMN_MEASURE + " TEXT NOT NULL," +
                RecipeIngredientContract.FavoritesRecipeEntry.COLUMN_INGREDIENT_DESC + " TEXT NOT NULL" +
                ")";

        db.execSQL(SQL_CREATE_PREFERRED_RECIPE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ RecipeIngredientContract.FavoritesRecipeEntry.TABLE_NAME);
        onCreate(db);
    }
}
