package com.centaury.domain.search.model

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 2/17/21.
 */
data class Search(
    val id: Int,
    val title: String,
    val titleBackground: String,
    val image: String,
    val genre: List<Int>,
    val overview: String,
    val date: String
)