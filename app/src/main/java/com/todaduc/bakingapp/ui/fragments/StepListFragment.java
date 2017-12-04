package com.todaduc.bakingapp.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private LinearLayoutManager mLayoutManager;
    private OnStepClickListener onStepClick;
    private Parcelable mListState;


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
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        List<BakingStep> backingSteps = new ArrayList<>();

        if( getArguments()!= null){
            backingSteps = this.getArguments().getParcelableArrayList(getString(R.string.activity_selected_recipe_steps));
        }
        /*if(savedInstanceState!= null){
            mListState = savedInstanceState.getParcelable("listState");
            Log.i("onCreateView", mListState.toString() );
            mLayoutManager.onRestoreInstanceState(mListState);
        }*/

        listStepsView.setLayoutManager(mLayoutManager);
        StepListAdapter stepListAdapter = new StepListAdapter(backingSteps, this);
        listStepsView.setAdapter(stepListAdapter);
        mLayoutManager.scrollToPosition(backingSteps.size() - 1);
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

   /* @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState!= null){
            mListState  =  savedInstanceState.getParcelable("listState");
            if(mListState!= null)
                Log.i("onViewCreated", mListState.toString());

        }
    }*/

    @Override
    public void onPause() {
        super.onPause();
        mListState = listStepsView.getLayoutManager().onSaveInstanceState();
    }

    @Override
    public void onResume() {
        super.onResume();
        //listStepsView.getLayoutManager().onRestoreInstanceState(mListState);
        //listStepsView.getScrollState();

    }

    /* @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.i("onSaveInstanceState", "onSaveInstanceState" );
        mListState = mLayoutManager.onSaveInstanceState();
        outState.putParcelable("listState", mListState);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null){
            mListState = savedInstanceState.getParcelable("listState");
            Log.i("onActivityCreated", mListState.toString() );
           // mLayoutManager.onRestoreInstanceState(mListState);
        }

    }*/

}
