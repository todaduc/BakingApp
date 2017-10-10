package com.todaduc.bakingapp.utilities;

import android.os.Handler;

/**
 * Created by ddjankou on 10/10/2017.
 */

public class RecipeRequestDelayer {

    private static final int DELAY_MILLIS = 3000;

    public interface DelayerCallBack{
        void onDone(String request);
    }


    static void processMessage(final String message, final DelayerCallBack callback,
                                final SimpleIdlingResource idlingResource) {
        // The IdlingResource is null in production.
        if (idlingResource != null) {
            idlingResource.setIdleState(false);
        }

        // Delay the execution, return message via callback.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onDone(message);
                    if (idlingResource != null) {
                        idlingResource.setIdleState(true);
                    }
                }
            }
        }, DELAY_MILLIS);
    }
}

