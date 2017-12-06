package com.todaduc.bakingapp;

import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.todaduc.bakingapp.ui.activities.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;


/**
 * Instrumentation test on the main activity.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static final String RECIPE_NAME = "Nutella Pie";
    private static final String BAKING_STEP_INTRO = "Recipe Introduction";
    private IdlingResource mIdlingResource;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingResource(){
         mIdlingResource = mActivityTestRule.getActivity().getIdlingResource();
         Espresso.registerIdlingResources(mIdlingResource);

    }


    /**
     * The click on a recipe a the position 1 is being tested, after a successful loading of
     * the recipe object.
     */
    @Test
    public void clickGridViewItem_OpensRecipeDetailActivity(){

        ViewInteraction textView = onView(
                allOf(withId(R.id.tv_recipe_name), withText(RECIPE_NAME),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recipe_card_view),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText(RECIPE_NAME)));

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recipe_grid_view),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction recyclerView1 = onView(
                allOf(withId(R.id.listStepView),
                        childAtPosition(
                                allOf(withId(R.id.recipe_steps_list),
                                        childAtPosition(
                                                withId(R.id.recipe_detail_container),
                                                0)),
                                2),
                        isDisplayed()));
        recyclerView1.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withText(RECIPE_NAME),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText(RECIPE_NAME)));

        ViewInteraction textView3 = onView(
                allOf(withText(RECIPE_NAME),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText(RECIPE_NAME)));

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.listStepView),
                        childAtPosition(
                                withId(R.id.recipe_steps_list),
                                2)));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction view = onView(
                allOf(withId(R.id.exo_shutter),
                        childAtPosition(
                                allOf(withId(R.id.exo_content_frame),
                                        childAtPosition(
                                                withId(R.id.media_player_view),
                                                0)),
                                0),
                        isDisplayed()));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.tv_step_detail), withText(BAKING_STEP_INTRO),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.step_instruction_container),
                                        0),
                                0),
                        isDisplayed()));
        textView4.check(matches(withText(BAKING_STEP_INTRO)));

    }

    @After
    public void unregisterIdlingResource(){
        if(mIdlingResource != null){
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
