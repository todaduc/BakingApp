package com.todaduc.bakingapp;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.todaduc.bakingapp.ui.activities.RecipeDetailActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;


/**
 * Instrumentation test on the recipe detail activity.
 */
@RunWith(AndroidJUnit4.class)
public class RecipeDetailActivityTest {

    @Rule
    public ActivityTestRule<RecipeDetailActivity> mActivityTestRule = new ActivityTestRule<>(RecipeDetailActivity.class);

    /**
     * The click on a backing step at the position 3 is being tested
     */
    @Test
    public void clickGridViewItem_OpensStepsDetailActivity(){

       //onView(ViewMatchers.withId(R.id.tv_recipe_serving));
       onView(ViewMatchers.withId(R.id.recipe_ingredients_container));
       onView(withId(R.id.recipe_detail_container));
       onView(withId(R.id.listStepView)).perform(RecyclerViewActions.actionOnItemAtPosition(3, ViewActions.click()));
      // onData(anything()).inAdapterView(withId(R.id.listStepView)).atPosition(1).perform(click());
        onView(withId(R.id.video_player_container));
             // .perform(RecyclerViewActions.actionOnItemAtPosition(3, ViewActions.click()));

    }
}
