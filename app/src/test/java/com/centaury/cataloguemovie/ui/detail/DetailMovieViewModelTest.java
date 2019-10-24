package com.centaury.cataloguemovie.ui.detail;

import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Centaury on 10/7/2019.
 */
public class DetailMovieViewModelTest {

    private DetailMovieViewModel detailMovieViewModel;
    private MovieEntity movieEntity;
    private TVShowEntity tvShowEntity;

    @Before
    public void setUp() {
        detailMovieViewModel = new DetailMovieViewModel();

        movieEntity = new MovieEntity(3,
                "The Old Man & the Gun",
                "https://image.tmdb.org/t/p/w500/a4BfxRK8dBgbQqbRxPs8kmLd8LG.jpg",
                "2018-09-28",
                "The true story of Forrest Tucker, from his audacious escape from San Quentin at the age of 70 to an unprecedented string of heists that confounded authorities and enchanted the public. Wrapped up in the pursuit are a detective, who becomes captivated with Forrest’s commitment to his craft, and a woman, who loves him in spite of his chosen profession.",
                "6.3");

        tvShowEntity = new TVShowEntity(5,
                "Grey's Anatomy",
                "https://image.tmdb.org/t/p/w500/jnsvc7gCKocXnrTXF6p03cICTWb.jpg",
                "2005-03-27",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "6.4");
    }

    @Test
    public void getMovie() {
        detailMovieViewModel.setMovieId(movieEntity.getMovieId());
        MovieEntity entity = detailMovieViewModel.getMovie();
        assertNotNull(entity);
        assertEquals(movieEntity.getMovieId(), entity.getMovieId());
        assertEquals(movieEntity.getName(), entity.getName());
        assertEquals(movieEntity.getDesc(), entity.getDesc());
        assertEquals(movieEntity.getImagePath(), entity.getImagePath());
        assertEquals(movieEntity.getDate(), entity.getDate());
        assertEquals(movieEntity.getRating(), entity.getRating());
    }

    @Test
    public void getTVShow() {
        detailMovieViewModel.setTvshowId(tvShowEntity.getTvshowId());
        TVShowEntity entity = detailMovieViewModel.getTvShow();
        assertNotNull(entity);
        assertEquals(tvShowEntity.getTvshowId(), entity.getTvshowId());
        assertEquals(tvShowEntity.getName(), entity.getName());
        assertEquals(tvShowEntity.getDesc(), entity.getDesc());
        assertEquals(tvShowEntity.getImagePath(), entity.getImagePath());
        assertEquals(tvShowEntity.getDate(), entity.getDate());
        assertEquals(tvShowEntity.getRating(), entity.getRating());
    }
}