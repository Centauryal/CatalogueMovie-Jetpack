package com.centaury.cataloguemovie.search.di

import com.centaury.cataloguemovie.search.SearchContract
import com.centaury.cataloguemovie.search.SearchViewModel
import dagger.Module
import dagger.Provides

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 2/20/21.
 */
@Module
class SearchModule {

    @Provides
    fun provideSearchViewModel(searchViewModel: SearchViewModel): SearchContract =
        searchViewModel
}