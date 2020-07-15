package com.centaury.data.genre.repository

import com.centaury.data.common.Source
import com.centaury.data.genre.mapper.GenreResultMapper
import com.centaury.data.genre.repository.source.GenreDataFactory
import com.centaury.domain.genre.GenreRepository
import com.centaury.domain.genre.model.Genre
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
class GenreEntityRepository @Inject constructor(
    private val genreDataFactory: GenreDataFactory,
    private val genreResultMapper: GenreResultMapper
) : GenreRepository {

    override fun getGenreMovies(): Observable<List<Genre>> =
        createGenreMovieData().genreMovies().map { genreResultMapper.transformGenreMovie(it) }

    override fun getGenreTVShows(): Observable<List<Genre>> =
        createGenreTVShowData().genreTVShows().map { genreResultMapper.transformGenreTVShow(it) }

    private fun createGenreMovieData(): GenreEntityData =
        genreDataFactory.createData(Source.NETWORK)

    private fun createGenreTVShowData(): GenreEntityData =
        genreDataFactory.createData(Source.NETWORK)

}