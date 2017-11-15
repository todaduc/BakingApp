package com.todaduc.bakingapp.utilities;

import android.os.Handler;
import com.todaduc.bakingapp.tasks.RecipeTask;


/**
 * Request delayer implementation that loads recipe list from the server,
 * when the main thread is put on hold.
 */
public class RecipeRequestDelayer {

    private static final int DELAY_MILLIS = 3000;

    /**
     * The onDone method will be call as callback method.
     */
    public interface DelayerCallBack{
        void onDone();
    }

    /**
     * This method process the recipe loading task while the UI thread is put on hold.
     * @param recipeTask the recipe task
     * @param callback the callback
     * @param idlingResource the idle resource
     */
    public static void processMessage(final RecipeTask recipeTask, final DelayerCallBack callback,
                                      final SimpleIdlingResource idlingResource) {
        if (idlingResource != null) {
            idlingResource.setIdleState(false);
        }

        // Delay the execution, return message via callback.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    recipeTask.execute();
                    callback.onDone();
                    if (idlingResource != null) {
                        idlingResource.setIdleState(true);
                    }
                }
            }
        }, DELAY_MILLIS);
    }
}

