package com.centaury.data.tvshow.repository.source.network.result.tvshow

import com.squareup.moshi.Json

data class TVShowResponse(

    @field:Json(name = "page")
    val page: Int,

    @field:Json(name = "total_pages")
    val totalPages: Int,

    @field:Json(name = "results")
    val results: List<ResultsItem>,

    @field:Json(name = "total_results")
    val totalResults: Int
)