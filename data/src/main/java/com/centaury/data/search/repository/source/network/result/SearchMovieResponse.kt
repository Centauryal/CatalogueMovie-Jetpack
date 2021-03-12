package com.centaury.data.search.repository.source.network.result

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class SearchMovieResponse(

    @field:Json(name = "page")
    val page: Int,

    @field:Json(name = "total_pages")
    val totalPages: Int,

    @field:Json(name = "results")
    val results: List<SearchMoviesItem>,

    @field:Json(name = "total_results")
    val totalResults: Int
)