package com.centaury.cataloguemovie.ui.tvshow;

import androidx.test.rule.ActivityTestRule;

import com.centaury.cataloguemovie.R;
import com.centaury.cataloguemovie.testing.SingleFragmentActivity;
import com.centaury.cataloguemovie.utils.RecyclerViewItemCountAssertion;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Centaury on 10/7/2019.
 */
public class TVShowFragmentTest {

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private TVShowFragment tvShowFragment = new TVShowFragment();

    @Before
    public void setUp() {
        activityRule.getActivity().setFragment(tvShowFragment);
    }

    @Test
    public void loadMovies() {
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tvshow)).check(new RecyclerViewItemCountAssertion(10));
    }

}