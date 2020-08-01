package com.centaury.domain.movies.model

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/29/2020.
 */
data class MoviesEntity(
    val id: Int,
    val title: String,
    val titleBackground: String,
    val image: String,
    val genre: String,
    val overview: String,
    val date: String
)