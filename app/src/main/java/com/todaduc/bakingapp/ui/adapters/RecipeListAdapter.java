package com.todaduc.bakingapp.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.BakingStep;
import com.todaduc.bakingapp.entities.Recipe;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * This Adapter populates a list of recipe.
 */
public class RecipeListAdapter  extends RecyclerView.Adapter<RecipeListAdapter.RecipeHolder>{

    private Context context;
    private List<Recipe> recipes;

    private OnRecipeClickListener onRecipeClickListener;


    /*
     * Interface that receives onRecipeClick messages.
     */
    public interface OnRecipeClickListener{
        void onRecipeClick(Recipe recipe);
    }

    public RecipeListAdapter(Context context, List<Recipe> recipeList, OnRecipeClickListener onRecipeClickListener) {
        this.context = context;
        this.recipes = recipeList;
        this.onRecipeClickListener = onRecipeClickListener;

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }


    @Override
    public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.recipe_card_item, parent, false);
        return new RecipeHolder(view);

    }

    @Override
    public void onBindViewHolder(RecipeHolder holder, int position) {
        holder.populate(recipes.get(position));
    }


    class RecipeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.tv_recipe_name)
        TextView recipeName;

        @BindView(R.id.tv_recipe_serving)
        TextView servings;

        @BindView(R.id.recipe_image)
        ImageView recipeImage;

        public RecipeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void populate(Recipe recipe){
            itemView.setTag(recipe);

            if(!TextUtils.isEmpty(recipe.getImage())){
                Picasso.with(context).load(recipe.getImage()).into(recipeImage);
            }

            recipeName.setText(recipe.getName());
            servings.setText(String.format(context.getString(R.string.servings_text), recipe.getServing()));

        }

        @Override
        public void onClick(View view) {
            if(view.getTag() instanceof  Recipe){
                Recipe recipe = (Recipe)view.getTag();
                onRecipeClickListener.onRecipeClick(recipe);
            }

        }
    }
}
