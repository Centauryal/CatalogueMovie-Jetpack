package com.centaury.cataloguemovie.search.di

import com.centaury.cataloguemovie.di.MovieScope
import com.centaury.cataloguemovie.di.component.AppComponent
import com.centaury.cataloguemovie.search.SearchActivity
import com.centaury.cataloguemovie.search.SearchViewModelModule
import dagger.Component

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 2/20/21.
 */
@MovieScope
@Component(
    dependencies = [AppComponent::class],
    modules = [SearchModule::class, SearchViewModelModule::class]
)
interface SearchComponent {
    fun inject(search: SearchActivity)
}