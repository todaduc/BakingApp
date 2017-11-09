package com.todaduc.bakingapp.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;



public class RecipeIngredientContentProvider extends ContentProvider{

    public static  final int FAVORITE_RECIPE =100;

    private RecipeIngredientDbHelper recipeIngredientDbHelper;
    @Override
    public boolean onCreate() {
        recipeIngredientDbHelper = new RecipeIngredientDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase database = recipeIngredientDbHelper.getWritableDatabase();
        int match = buildUriMatcher().match(uri);

        Cursor cursor;

        switch (match){
            case FAVORITE_RECIPE:
                cursor=database.query(RecipeIngredientContract.FavoritesRecipeEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException(" Unknown uri: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    public static UriMatcher buildUriMatcher(){
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(RecipeIngredientContract.AUTHORITY,RecipeIngredientContract.PATH_FAVORITES, FAVORITE_RECIPE);

        return matcher;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final SQLiteDatabase database = recipeIngredientDbHelper.getWritableDatabase();

        int match = buildUriMatcher().match(uri);
        Uri returnUri;

        switch (match){
            case FAVORITE_RECIPE:
                long id = database.insert(RecipeIngredientContract.FavoritesRecipeEntry.TABLE_NAME,null,contentValues);
                if(id >0){
                    returnUri = ContentUris.withAppendedId(RecipeIngredientContract.FavoritesRecipeEntry.CONTENT_URI,id);
                }else
                {
                    throw new android.database.SQLException("failed to insert row into " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException(" Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri,null);
        return returnUri;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        final SQLiteDatabase database = recipeIngredientDbHelper.getWritableDatabase();
        int match = buildUriMatcher().match(uri);
        int id = 0;

        switch (match){
            case FAVORITE_RECIPE:
                id = database.delete(RecipeIngredientContract.FavoritesRecipeEntry.TABLE_NAME,s,strings);
                break;
            default:
                throw new UnsupportedOperationException(" Unknown uri: " + uri);
        }

       return id;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
