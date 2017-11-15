package com.todaduc.bakingapp.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.BakingStep;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * This Adapter populates a list of backing steps to a RecyclerView.
 */
public class StepListAdapter  extends RecyclerView.Adapter<StepListAdapter.StepHolder> {

   private List<BakingStep> bakingSteps;
   private OnBakingStepClickListener onBakingStepClickListener;


    /*
     * Interface that receives onBakingStepClick messages.
     */
   public interface OnBakingStepClickListener{
      void onBakingStepClick(BakingStep bakingStep);
   }

   public StepListAdapter(List<BakingStep> bakingSteps, OnBakingStepClickListener onBakingStepClickListener){
       this.bakingSteps = bakingSteps;
       this.onBakingStepClickListener = onBakingStepClickListener;
   }

    @Override
    public StepHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       Context context = parent.getContext();
       int layoutId = R.layout.recipe_step_item;
       LayoutInflater inflater = LayoutInflater.from(context);

       View view = inflater.inflate(layoutId, parent, false);
       return new StepHolder(view);
    }

    @Override
    public void onBindViewHolder(StepHolder holder, int position) {
        holder.populate(bakingSteps.get(position));
    }

    @Override
    public int getItemCount() {
        return bakingSteps.size();
    }

    /**
     * View holder definition for the backing step adapter.
     */
   class StepHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       @BindView(R.id.tv_step_description)
       TextView mStepDescription;

       public StepHolder(View itemView){
           super(itemView);
           ButterKnife.bind(this, itemView);
           itemView.setOnClickListener(this);
       }

        /**
         * This method populates the baking step description on the view holder.
         * @param bakingStep The baking step object
         */
       public void populate(BakingStep bakingStep){
           itemView.setTag(bakingStep);
           mStepDescription.setText(bakingStep.getDescription());
       }

        /**
         * This gets called by the child views during a click.
         *
         * @param v The View that was clicked
         */
       @Override
       public void onClick(View v) {
           if(v.getTag() instanceof  BakingStep){
               BakingStep bakingStep = (BakingStep)v.getTag();
               onBakingStepClickListener.onBakingStepClick(bakingStep);
           }
       }
   }
}
