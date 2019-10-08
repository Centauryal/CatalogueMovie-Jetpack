package com.centaury.cataloguemovie.ui.detail;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.centaury.cataloguemovie.R;
import com.centaury.cataloguemovie.data.MovieEntity;
import com.centaury.cataloguemovie.utils.FakeDataDummy;

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

    private MovieEntity movieEntity = FakeDataDummy.generateDummyMovies().get(0);

    @Rule
    public ActivityTestRule<DetailMovieActivity> activityRule = new ActivityTestRule<DetailMovieActivity>(DetailMovieActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intent = new Intent(targetContext, DetailMovieActivity.class);
            intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movieEntity.getMovieId());
            return intent;
        }
    };

    @Test
    public void loadMovie() {
        onView(withId(R.id.txt_titledetail)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_titledetail)).check(matches(withText(movieEntity.getName())));
        onView(withId(R.id.txt_ratemovie)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_ratemovie)).check(matches(withText(movieEntity.getRating())));
    }

}