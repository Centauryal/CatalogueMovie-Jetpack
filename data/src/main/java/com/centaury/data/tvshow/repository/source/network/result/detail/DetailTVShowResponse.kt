package com.centaury.data.tvshow.repository.source.network.result.detail

import com.squareup.moshi.Json

data class DetailTVShowResponse(

    @field:Json(name = "original_language")
    val originalLanguage: String,

    @field:Json(name = "number_of_episodes")
    val numberOfEpisodes: Int,

    @field:Json(name = "networks")
    val networks: List<NetworksItem>,

    @field:Json(name = "type")
    val type: String,

    @field:Json(name = "backdrop_path")
    val backdropPath: String,

    @field:Json(name = "genres")
    val genres: List<GenresItem>,

    @field:Json(name = "popularity")
    val popularity: Double,

    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "number_of_seasons")
    val numberOfSeasons: Int,

    @field:Json(name = "vote_count")
    val voteCount: Int,

    @field:Json(name = "first_air_date")
    val firstAirDate: String,

    @field:Json(name = "overview")
    val overview: String,

    @field:Json(name = "seasons")
    val seasons: List<SeasonsItem>,

    @field:Json(name = "languages")
    val languages: List<String>,

    @field:Json(name = "created_by")
    val createdBy: List<CreatedByItem>,

    @field:Json(name = "last_episode_to_air")
    val lastEpisodeToAir: LastEpisodeToAir,

    @field:Json(name = "poster_path")
    val posterPath: String,

    @field:Json(name = "origin_country")
    val originCountry: List<String>,

    @field:Json(name = "production_companies")
    val productionCompanies: List<ProductionCompaniesItem>,

    @field:Json(name = "original_name")
    val originalName: String,

    @field:Json(name = "vote_average")
    val voteAverage: Double,

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "episode_run_time")
    val episodeRunTime: List<Int>,

    @field:Json(name = "next_episode_to_air")
    val nextEpisodeToAir: Any,

    @field:Json(name = "in_production")
    val inProduction: Boolean,

    @field:Json(name = "last_air_date")
    val lastAirDate: String,

    @field:Json(name = "homepage")
    val homepage: String,

    @field:Json(name = "status")
    val status: String
)