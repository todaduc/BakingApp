package com.todaduc.bakingapp.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.Ingredient;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * This Adapter populates a list of recipe ingredients to a RecyclerView.
 */
public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientHolder>{

    private List<Ingredient> mlistIngredient;
    private Context context;

    public IngredientAdapter(Context context, List<Ingredient> mlistIngredient) {
        this.context = context;
        this.mlistIngredient = mlistIngredient;
    }

    @Override
    public IngredientHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.ingredient_list_item, parent, false);
        return new IngredientHolder(view);

    }

    @Override
    public void onBindViewHolder(IngredientHolder holder, int position) {
        holder.populate(mlistIngredient.get(position));
    }

    @Override
    public int getItemCount() {
        return mlistIngredient.size();
    }

    /**
     * View holder definition of the ingredient adapter.
     */
    class IngredientHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_ingredient_desc)
        TextView mIngredientDescription;

        public IngredientHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void populate(Ingredient ingredient){
            itemView.setTag(ingredient);

            mIngredientDescription.setText(String.format(context.getString(R.string.ingredient_text_format), ingredient.getQuantity(), ingredient.getMeasure(), ingredient.getDescription()));

        }

    }
}
