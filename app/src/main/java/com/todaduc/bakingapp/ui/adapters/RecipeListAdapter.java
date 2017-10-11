package com.todaduc.bakingapp.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.Recipe;
import com.todaduc.bakingapp.ui.fragments.RecipeListFragment;

import java.util.List;

/**
 * Created by Themener on 8/23/17.
 */

public class RecipeListAdapter  extends BaseAdapter{

    private Context context;
    private List<Recipe> recipes;
    private final OnRecipeClickListener mOnClickListener;

    public interface OnRecipeClickListener{
        void onCardSelected(Recipe recipe);
    }

    public RecipeListAdapter(Context context, List<Recipe> recipeList, OnRecipeClickListener mOnClickListener) {
        this.context = context;
        this.recipes = recipeList;
        this.mOnClickListener = mOnClickListener;
    }

    @Override
    public int getCount() {
        return recipes.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public View getView (final  int position, View convertView, ViewGroup parent){

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.fragment_recipe, parent, false);
        }
        ((TextView)convertView.findViewById(R.id.tv_recipe_name)).setText(recipes.get(position).getName());
        ((TextView)convertView.findViewById(R.id.tv_recipe_serving)).setText("Servings: " + recipes.get(position).getServing());

        // Set the image resource and return the newly created ImageView
        return convertView;
    }
}
