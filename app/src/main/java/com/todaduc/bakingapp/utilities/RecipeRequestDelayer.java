package com.todaduc.bakingapp.utilities;

import android.os.Handler;

import com.todaduc.bakingapp.entities.Recipe;
import com.todaduc.bakingapp.tasks.RecipeTask;

import java.util.List;

/**
 * Created by ddjankou on 10/10/2017.
 */

public class RecipeRequestDelayer {

    private static final int DELAY_MILLIS = 3000;

    public interface DelayerCallBack{
        void onDone(List<Recipe> recipeList);
    }


    public static void processMessage(final RecipeTask recipeTask, final DelayerCallBack callback,
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
                    recipeTask.execute();
                    callback.onDone(recipeTask.getRecipes());
                    if (idlingResource != null) {
                        idlingResource.setIdleState(true);
                    }
                }
            }
        }, DELAY_MILLIS);
    }
}

