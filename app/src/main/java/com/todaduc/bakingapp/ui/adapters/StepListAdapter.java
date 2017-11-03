package com.todaduc.bakingapp.ui.adapters;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.BakingStep;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ddjankou on 8/29/2017.
 */

public class StepListAdapter  extends RecyclerView.Adapter<StepListAdapter.StepHolder> {

   private List<BakingStep> bakingSteps;

   private OnBakingStepClickListener onBakingStepClickListener;
   interface OnBakingStepClickListener{
      void onBakingStepClick(BakingStep bakingStep);
   }

   public StepListAdapter(List<BakingStep> bakingSteps){
       this.bakingSteps = bakingSteps;
   }

    @Override
    public StepHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       Context context = parent.getContext();
       int layoutId = R.layout.fragment_step;
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




   class StepHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       @BindView(R.id.tv_step_description)
       TextView mStepDescription;

       public StepHolder(View itemView){
           super(itemView);
           ButterKnife.bind(this, itemView);
           itemView.setOnClickListener(this);
       }

       public void populate(BakingStep bakingStep){
           itemView.setTag(bakingStep);
           mStepDescription.setText(bakingStep.getDescription());
       }

       @Override
       public void onClick(View v) {
           if(v.getTag() instanceof  BakingStep){
               BakingStep bakingStep = (BakingStep)v.getTag();
               onBakingStepClickListener.onBakingStepClick(bakingStep);
           }
       }
   }
}
