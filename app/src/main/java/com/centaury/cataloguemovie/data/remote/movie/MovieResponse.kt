package com.centaury.cataloguemovie.data.remote.movie

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(

    @Json(name = "page")
    val page: Int? = null,

    @Json(name = "total_pages")
    val totalPages: Int? = null,

    @Json(name = "results")
    val results: List<ResultsItem?>? = null,

    @Json(name = "total_results")
    val totalResults: Int? = null
)