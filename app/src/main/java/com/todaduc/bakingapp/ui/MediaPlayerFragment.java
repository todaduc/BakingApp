package com.todaduc.bakingapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;
import com.todaduc.bakingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ddjankou on 8/29/2017.
 */

public class MediaPlayerFragment extends Fragment {

    @BindView(R.id.media_player_view)
    VideoView videoView;

    public MediaPlayerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_media_player, container, false);
        ButterKnife.bind(this,rootView);

        String videoUrl = null;

        if( getArguments()!= null){
            videoUrl = getArguments().getString("Video");
        }

        return rootView;
    }

}
