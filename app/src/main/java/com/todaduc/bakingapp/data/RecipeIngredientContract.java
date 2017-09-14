package com.todaduc.bakingapp.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by ddjankou on 9/14/2017.
 */

public class RecipeIngredientContract {

    public static final String AUTHORITY = "com.todaduc.bakingapp";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_FAVORITES = "favoritesRecipe";

    public static final class FavoritesRecipeEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVORITES).build();

        public static final String TABLE_NAME = "favoritesRecipe";

        public static final String COLUMN_RECIPE_NAME = "recipeTitle";

        public static final String COLUMN_QUANTITY = "quantity";

        public static  final String COLUMN_MEASURE = "measure";

        public static  final String COLUMN_INGREDIENT_DESC = "description";
    }
}
