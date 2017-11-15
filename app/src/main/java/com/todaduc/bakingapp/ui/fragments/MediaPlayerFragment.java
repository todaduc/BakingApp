package com.todaduc.bakingapp.ui.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.todaduc.bakingapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * This class hosts the application media player on a fragment element.
 */
public class MediaPlayerFragment extends Fragment {

    @BindView(R.id.media_player_view)
    SimpleExoPlayerView mPlayerView;
    private SimpleExoPlayer mExoPlayer;


    public MediaPlayerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_media_player, container, false);
        ButterKnife.bind(this,rootView);


        String videoUrl;

        if( getArguments()!= null){
            videoUrl = getArguments().getString(getString(R.string.activity_selected_recipe_video));

            if(videoUrl!= null && !videoUrl.isEmpty()){
                initializePlayer(Uri.parse(videoUrl));
            }
        }

        return rootView;
    }

    /**
     * Creates an instance of the ExoPlayer.
     * @param mediaUri the media url
     */
    private void initializePlayer(Uri mediaUri) {
        if (mExoPlayer == null) {

            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);

            String userAgent = Util.getUserAgent(getContext(), getString(R.string.app_name_user_agent));
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                        getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    private void releasePlayer() {
        if(mExoPlayer != null){
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }
}
