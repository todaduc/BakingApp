package com.todaduc.bakingapp.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.Ingredient;
import java.util.List;

/**
 * Created by ddjankou on 8/29/2017.
 */

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientHolder>{

    private List<Ingredient> mlistIngredient;

    public IngredientAdapter(List<Ingredient> mlistIngredient) {
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

    class IngredientHolder extends RecyclerView.ViewHolder{
        final TextView mIngredientDescription;
        final TextView mQuantity;

        public IngredientHolder(View itemView) {
            super(itemView);
            mIngredientDescription = (TextView)itemView.findViewById(R.id.tv_ingredient_desc);
            mQuantity = (TextView) itemView.findViewById(R.id.tv_quantity);
        }

        public void populate(Ingredient ingredient){
            itemView.setTag(ingredient);
           // mIngredientDescription.setText(ingredient.getDescription());
            mQuantity.setText(ingredient.getQuantity().concat(" "+ingredient.getMeasure().concat(" of ").concat(ingredient.getDescription())));

        }

    }
}
