package com.centaury.cataloguemovie.ui.movie;

import com.centaury.cataloguemovie.data.MovieEntity;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Centaury on 10/7/2019.
 */
public class MovieViewModelTest {
    private MovieViewModel movieViewModel;

    @Before
    public void setUp() {
        movieViewModel = new MovieViewModel();
    }

    @Test
    public void getMovies() {
        List<MovieEntity> movieEntities = movieViewModel.getMovies();
        assertNotNull(movieEntities);
        assertEquals(10, movieEntities.size());
    }
}