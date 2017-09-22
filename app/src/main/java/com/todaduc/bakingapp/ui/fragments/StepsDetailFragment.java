package com.todaduc.bakingapp.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.todaduc.bakingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ddjankou on 8/24/2017.
 */

public class StepsDetailFragment extends Fragment {

    @BindView(R.id.tv_step_detail)
    TextView stepDetail;

    public StepsDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_steps_detail,container, false);
        ButterKnife.bind(this, rootView);

        String description = "";

        if( getArguments()!= null){
            description = getArguments().getString("Description");
        }

        stepDetail.setText(description);

        return rootView;
    }
}
