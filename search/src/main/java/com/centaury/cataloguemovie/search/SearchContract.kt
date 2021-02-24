package com.centaury.cataloguemovie.search

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 2/20/21.
 */
interface SearchContract {
    fun getAllSearchMoviesContract(query: String)

    fun getAllSearchTVShowsContract(query: String)

    fun getGenreMovieContract()

    fun getGenreTVShowContract()
}