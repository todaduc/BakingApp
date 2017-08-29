package com.todaduc.bakingapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.todaduc.bakingapp.R;

/**
 * Created by ddjankou on 8/29/2017.
 */

public class MediaPlayerFragment extends Fragment {

    public MediaPlayerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_media_player, container, false);

        VideoView videoView = (VideoView)rootView.findViewById(R.id.media_player_view);

        return rootView;
    }

}
