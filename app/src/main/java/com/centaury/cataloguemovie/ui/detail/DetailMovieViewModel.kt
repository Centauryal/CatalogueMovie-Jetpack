package com.centaury.cataloguemovie.ui.detail

/**
 * Created by Centaury on 10/7/2019.
 */
/*
class DetailMovieViewModel @Inject constructor(private val catalogueRepository: CatalogueRepository) :
    ViewModel() {
    private var movieId: String? = null
    private var tvshowId: String? = null
    fun getDetailMovie(language: String?): LiveData<DetailMovieResponse> {
        return catalogueRepository.getDetailMovie(movieId, language)
    }

    fun getDetailTVShow(language: String?): LiveData<DetailTVShowResponse> {
        return catalogueRepository.getDetailTVShow(tvshowId, language)
    }

    @Throws(ExecutionException::class, InterruptedException::class)
    fun getDetailFavMovie(id: Int): MovieEntity {
        return catalogueRepository.getDetailFavMovie(id)
    }

    @Throws(ExecutionException::class, InterruptedException::class)
    fun getDetailFavTVShow(id: Int): TVShowEntity {
        return catalogueRepository.getDetailFavTVShow(id)
    }

    fun setMovieId(movieId: String?) {
        this.movieId = movieId
    }

    fun setTvshowId(tvshowId: String?) {
        this.tvshowId = tvshowId
    }

    fun insertFavoriteMovie(movieEntity: MovieEntity?) {
        catalogueRepository.insertFavMovie(movieEntity)
    }

    fun deleteFavoriteMovie(movieEntity: MovieEntity?) {
        catalogueRepository.deleteFavMovie(movieEntity)
    }

    fun insertFavoriteTVShow(tvShowEntity: TVShowEntity?) {
        catalogueRepository.insertFavTVShow(tvShowEntity)
    }

    fun deleteFavoriteTVShow(tvShowEntity: TVShowEntity?) {
        catalogueRepository.deleteFavTVShow(tvShowEntity)
    }

}*/
