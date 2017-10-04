package com.todaduc.bakingapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.todaduc.bakingapp.ui.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

/**
 * Created by Themener on 10/3/17.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    public static final String RECIPE_NAME = "Brownies";

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void clickGridViewItem_OpensRecipeDetailActivity(){

        onData(anything()).inAdapterView(withId(R.id.recipe_grid_view)).atPosition(1).perform(click());
        onView(withId(R.id.recipe_ingredients_container));
        onView(withId(R.id.recipe_detail_container));

        // Checks that the OrderActivity opens with the correct tea name displayed
       onView(withId(R.string.activity_label)).check(matches(withText(RECIPE_NAME)));
    }
}
