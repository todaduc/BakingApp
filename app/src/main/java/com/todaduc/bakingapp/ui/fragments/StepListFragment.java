package com.todaduc.bakingapp.ui.fragments;

import android.content.Context;
import android.os.Bundle;
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
 * Created by ddjankou on 8/24/2017.
 */

public class StepListFragment extends Fragment implements StepListAdapter.OnBakingStepClickListener{

    List<BakingStep> backingSteps = new ArrayList<>();

    @BindView(R.id.listView)
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

        if( getArguments()!= null){
            backingSteps = this.getArguments().getParcelableArrayList("RecipeSteps");
        }

        listStepsView.setLayoutManager(mLayoutManager);
        StepListAdapter stepListAdapter = new StepListAdapter(backingSteps, this);
        listStepsView.setAdapter(stepListAdapter);

        return rootView;
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
