package com.todaduc.bakingapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.todaduc.bakingapp.R;

/**
 * Created by ddjankou on 8/24/2017.
 */

public class StepsDetailFragment extends Fragment {

    public StepsDetailFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_steps_detail,container, false);
        return rootView;
    }
}
