package com.centaury.cataloguemovie.ui.tvshow;

import com.centaury.cataloguemovie.data.TVShowEntity;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Centaury on 10/7/2019.
 */
public class TVShowViewModelTest {

    private TVShowViewModel tvShowViewModel;

    @Before
    public void setUp() {
        tvShowViewModel = new TVShowViewModel();
    }

    @Test
    public void getTVShows() {
        List<TVShowEntity> tvShowEntities = tvShowViewModel.getTVShows();
        assertNotNull(tvShowEntities);
        assertEquals(10, tvShowEntities.size());
    }
}