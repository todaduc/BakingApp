package com.todaduc.bakingapp.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.Recipe;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


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

        //((TextView)convertView.findViewById(R.id.tv_recipe_serving)).setText("Servings: " + recipes.get(position).getServing());

         holder = (ViewHolder)convertView.getTag();
         holder.recipeName.setText(recipes.get(position).getName());
         holder.servings.setText(String.format(context.getString(R.string.servings_text), recipes.get(position).getServing()));

        return convertView;
    }

    static class ViewHolder{
        @BindView(R.id.tv_recipe_name)
        TextView recipeName;
        @BindView(R.id.tv_recipe_serving)
        TextView servings;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
