package com.centaury.cataloguemovie.ui.tvshow;

import androidx.lifecycle.ViewModel;

import com.centaury.cataloguemovie.data.TVShowEntity;
import com.centaury.cataloguemovie.utils.DataDummy;

import java.util.List;

/**
 * Created by Centaury on 10/7/2019.
 */
public class TVShowViewModel extends ViewModel {

    public List<TVShowEntity> getTVShows() {
        return DataDummy.generateDummyTVShows();
    }
}
