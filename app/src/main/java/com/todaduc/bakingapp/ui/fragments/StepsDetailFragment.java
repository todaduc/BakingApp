package com.todaduc.bakingapp.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.todaduc.bakingapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * This class hosts the baking step description on a fragment element.
 */
public class StepsDetailFragment extends Fragment {

    @BindView(R.id.tv_step_detail)
    TextView stepDetail;

    @BindView(R.id.steps_thumbnail)
    ImageView thumbnailView;

    public StepsDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_steps_detail,container, false);
        ButterKnife.bind(this, rootView);

        String description = "";
        String thumbnail = "";
        if( getArguments()!= null){
            description = getArguments().getString(getString(R.string.activity_selected_recipe_desc));
            thumbnail = getArguments().getString(getString(R.string.thumbnail_url));
        }
        if(!TextUtils.isEmpty(thumbnail)){
            Picasso.with(getContext()).load(thumbnail).into(thumbnailView);

        }
        stepDetail.setText(description);

        return rootView;
    }
}
