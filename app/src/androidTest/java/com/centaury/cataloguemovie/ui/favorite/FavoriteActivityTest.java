package com.centaury.cataloguemovie.ui.favorite;

import androidx.test.rule.ActivityTestRule;

import com.centaury.cataloguemovie.R;

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
 * Created by Centaury on 11/26/2019.
 */
public class FavoriteActivityTest {

    @Rule
    public ActivityTestRule<FavoriteActivity> activityTestRule = new ActivityTestRule<>(FavoriteActivity.class);

    @Test
    public void loadtabMovieFavorite() {
        onView(allOf(withText(R.string.title_movie), isDescendantOfA(withId(R.id.fav_tabs)))).perform(click());
        onView(withId(R.id.rv_favmovie)).check(matches(isDisplayed()));
    }

    @Test
    public void loadtabTVShowFavorite() {
        onView(allOf(withText(R.string.title_tv_show), isDescendantOfA(withId(R.id.fav_tabs)))).perform(click());
        onView(withId(R.id.rv_favtvshow)).check(matches(isDisplayed()));
    }
}