package com.todaduc.bakingapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.BakingStep;
import com.todaduc.bakingapp.entities.Ingredient;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ddjankou on 8/24/2017.
 */

public class StepListFragment extends Fragment {

    List<BakingStep> backingSteps = new ArrayList<>();

    private OnStepClickListener onStepClick;

    interface OnStepClickListener{
        void onStepSelected(int position);
    }
    {

        backingSteps.add(new BakingStep(1,"a little test ", "more test","",""));
        backingSteps.add(new BakingStep(2,"a little test ", "more test","",""));
        backingSteps.add(new BakingStep(3,"a little test ", "more test","",""));
        backingSteps.add(new BakingStep(4,"a little test ", "more test","",""));
        backingSteps.add(new BakingStep(5,"a little test ", "more test","",""));

    }

    public StepListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_step_list,container, false);

        GridView listStepsView = (GridView)rootView.findViewById(R.id.steps_grid_view);

        StepListAdapter stepListAdapter = new StepListAdapter(getContext(), backingSteps );

        listStepsView.setAdapter(stepListAdapter);

        listStepsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onStepClick.onStepSelected(position);
            }
        });
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
