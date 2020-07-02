package com.centaury.cataloguemovie.data.remote.detail.tvshow

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailTVShowResponse(

    @Json(name = "original_language")
    val originalLanguage: String? = null,

    @Json(name = "number_of_episodes")
    val numberOfEpisodes: Int? = null,

    @Json(name = "networks")
    val networks: List<NetworksItem?>? = null,

    @Json(name = "type")
    val type: String? = null,

    @Json(name = "backdrop_path")
    val backdropPath: String? = null,

    @Json(name = "genres")
    val genres: List<TVShowGenresItem?>? = null,

    @Json(name = "popularity")
    val popularity: Double? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "number_of_seasons")
    val numberOfSeasons: Int? = null,

    @Json(name = "vote_count")
    val voteCount: Int? = null,

    @Json(name = "first_air_date")
    val firstAirDate: String? = null,

    @Json(name = "overview")
    val overview: String? = null,

    @Json(name = "seasons")
    val seasons: List<SeasonsItem?>? = null,

    @Json(name = "languages")
    val languages: List<String?>? = null,

    @Json(name = "created_by")
    val createdBy: List<CreatedByItem?>? = null,

    @Json(name = "last_episode_to_air")
    val lastEpisodeToAir: LastEpisodeToAir? = null,

    @Json(name = "poster_path")
    val posterPath: String? = null,

    @Json(name = "origin_country")
    val originCountry: List<String?>? = null,

    @Json(name = "production_companies")
    val productionCompanies: List<ProductionCompaniesItem?>? = null,

    @Json(name = "original_name")
    val originalName: String? = null,

    @Json(name = "vote_average")
    val voteAverage: Double? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "episode_run_time")
    val episodeRunTime: List<Int?>? = null,

    @Json(name = "next_episode_to_air")
    val nextEpisodeToAir: Any? = null,

    @Json(name = "in_production")
    val inProduction: Boolean? = null,

    @Json(name = "last_air_date")
    val lastAirDate: String? = null,

    @Json(name = "homepage")
    val homepage: String? = null,

    @Json(name = "status")
    val status: String? = null
)