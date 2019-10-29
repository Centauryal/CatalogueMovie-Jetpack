package com.centaury.cataloguemovie.ui.detail;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.centaury.cataloguemovie.R;
import com.centaury.cataloguemovie.data.remote.detail.movie.DetailMovieResponse;
import com.centaury.cataloguemovie.utils.AppConstants;
import com.centaury.cataloguemovie.utils.EspressoIdlingResource;
import com.centaury.cataloguemovie.utils.FakeDataDummy;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Centaury on 10/7/2019.
 */
public class DetailMovieActivityTest {

    private DetailMovieResponse detailMovieResponse = FakeDataDummy.generateDummyDetailMovies().get(0);

    @Rule
    public ActivityTestRule<DetailMovieActivity> activityRule = new ActivityTestRule<DetailMovieActivity>(DetailMovieActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intent = new Intent(targetContext, DetailMovieActivity.class);
            intent.putExtra(AppConstants.DETAIL_EXTRA_MOVIE, detailMovieResponse.getId());
            return intent;
        }
    };

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadMovie() {
        onView(withId(R.id.txt_titledetail)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_titledetail)).check(matches(withText(detailMovieResponse.getTitle())));
        onView(withId(R.id.txt_descdetail)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_descdetail)).check(matches(withText(detailMovieResponse.getOverview())));
    }

}