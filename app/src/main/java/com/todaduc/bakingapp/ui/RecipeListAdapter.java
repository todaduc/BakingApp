package com.todaduc.bakingapp.ui;

import android.content.Context;
import android.nfc.cardemulation.CardEmulation;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.Recipe;

import java.util.List;

/**
 * Created by Themener on 8/23/17.
 */

public class RecipeListAdapter  extends BaseAdapter{

    private Context context;
    private List<Recipe> recipes;

    public RecipeListAdapter(Context context, List<Recipe> recipeList) {
        this.context = context;
        this.recipes = recipeList;
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

    public View getView (final  int position, View convertView, ViewGroup parent){

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);

           convertView = inflater.inflate(R.layout.fragment_recipe, parent, false);
        }
        ((TextView)convertView.findViewById(R.id.info_text)).setText(recipes.get(position).getName());
        // Set the image resource and return the newly created ImageView
        return convertView;
    }
}
