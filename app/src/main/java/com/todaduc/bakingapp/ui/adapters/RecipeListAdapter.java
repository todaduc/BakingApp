package com.todaduc.bakingapp.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.Recipe;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * This Adapter populates a list of recipe.
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

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public View getView (final  int position, View convertView, ViewGroup parent){
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.recipe_card_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

         holder = (ViewHolder)convertView.getTag();

        Recipe recipe = recipes.get(position);
        if(recipe.getImage()!= null && !recipe.getImage().isEmpty()){
            Picasso.with(context).load(context.getString(R.string.image_base_url).concat(recipe.getImage())).into(holder.recipeImage);
        }

        holder.recipeName.setText(recipe.getName());
        holder.servings.setText(String.format(context.getString(R.string.servings_text), recipe.getServing()));

        return convertView;
    }

    /**
     * View holder definition.
     */
    static class ViewHolder{

        @BindView(R.id.tv_recipe_name)
        TextView recipeName;

        @BindView(R.id.tv_recipe_serving)
        TextView servings;

        @BindView(R.id.recipe_image)
        ImageView recipeImage;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
