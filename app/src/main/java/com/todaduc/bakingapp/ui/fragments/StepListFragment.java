package com.todaduc.bakingapp.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.BakingStep;
import com.todaduc.bakingapp.ui.adapters.StepListAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * This class populates the list of baking steps on a fragment element.
 */
public class StepListFragment extends Fragment implements StepListAdapter.OnBakingStepClickListener{

    @BindView(R.id.listStepView)
    RecyclerView listStepsView;

    private OnStepClickListener onStepClick;

    @Override
    public void onBakingStepClick(BakingStep bakingStep) {
        onStepClick.onStepSelected(bakingStep);
    }

    public interface OnStepClickListener{
        void onStepSelected(BakingStep bakingStep);
    }

    public StepListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_step_list,container, false);
        ButterKnife.bind(this,rootView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        List<BakingStep> backingSteps = new ArrayList<>();

        if( getArguments()!= null){
            backingSteps = this.getArguments().getParcelableArrayList(getString(R.string.activity_selected_recipe_steps));
        }

        listStepsView.setLayoutManager(mLayoutManager);
        StepListAdapter stepListAdapter = new StepListAdapter(backingSteps, this);
        listStepsView.setAdapter(stepListAdapter);

        return rootView;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if(savedInstanceState != null){
            Parcelable recyclerLayout = savedInstanceState.getParcelable("recyclerLayout");
            listStepsView.getLayoutManager().onRestoreInstanceState(recyclerLayout);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("recyclerLayout", listStepsView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            onStepClick = (OnStepClickListener)context;
        }catch (ClassCastException e){
            throw  new ClassCastException(context.toString()+ " must implement OnStepClickListener");
        }

    }
}
