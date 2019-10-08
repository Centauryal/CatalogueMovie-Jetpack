package com.centaury.cataloguemovie.ui;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.centaury.cataloguemovie.R;
import com.centaury.cataloguemovie.ui.main.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by Centaury on 10/8/2019.
 */
public class MovieCatalogueTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void movieToDetailActivityTest() {
        onView(allOf(withText(R.string.title_movie), isDescendantOfA(withId(R.id.tabs)))).perform(click());
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.txt_titledetail)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_titledetail)).check(matches(withText("Joker")));
    }

    @Test
    public void tvshowToDetailActivityTest() {
        onView(allOf(withText(R.string.title_tv_show), isDescendantOfA(withId(R.id.tabs)))).perform(click());
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.txt_titledetail)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_titledetail)).check(matches(withText("The Simpsons")));
    }
}
