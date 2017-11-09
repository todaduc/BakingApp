package com.todaduc.bakingapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.todaduc.bakingapp.ui.activities.MainActivity;

import org.junit.After;
import org.junit.Before;
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



@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    public static final String RECIPE_NAME = "Brownies";
    private IdlingResource mIdlingResource;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingResource(){
         mIdlingResource = mActivityTestRule.getActivity().getSimpleIdlingResource();
         Espresso.registerIdlingResources(mIdlingResource);

    }


    @Test
    public void clickGridViewItem_OpensRecipeDetailActivity(){

        onData(anything()).inAdapterView(withId(R.id.recipe_grid_view)).atPosition(1).perform(click());
        onView(withId(R.id.recipe_ingredients_container));
        onView(withId(R.id.recipe_detail_container));


        onView(withId(R.string.activity_label)).check(matches(withText(RECIPE_NAME)));
    }

    @After
    public void unregisterIdlingResource(){
        if(mIdlingResource != null){
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }
}
