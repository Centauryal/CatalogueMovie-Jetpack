package com.centaury.domain.movies.model

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
data class Movie(
    val id: Int,
    val title: String,
    val titleBackground: String,
    val image: String,
    val genre: List<Int>,
    val overview: String,
    val date: String
)