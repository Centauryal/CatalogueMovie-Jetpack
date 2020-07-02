package com.centaury.cataloguemovie.data.remote.tvshow

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TVShowResponse(

    @Json(name = "page")
    val page: Int? = null,

    @Json(name = "total_pages")
    val totalPages: Int? = null,

    @Json(name = "results")
    val results: List<TVShowResultsItem?>? = null,

    @Json(name = "total_results")
    val totalResults: Int? = null
)