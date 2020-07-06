package com.centaury.data.movies.repository.source.network.result.movie

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(

    @Json(name = "page")
    val page: Int,

    @Json(name = "total_pages")
    val totalPages: Int,

    @Json(name = "results")
    val results: List<ResultsItem>,

    @Json(name = "total_results")
    val totalResults: Int
)