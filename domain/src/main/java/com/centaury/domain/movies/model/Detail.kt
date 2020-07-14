package com.centaury.domain.movies.model

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
data class Detail(
    val id: Int,
    val title: String,
    val image: String,
    val imageBackground: String,
    val genre: String,
    val vote: String,
    val rating: Float,
    val date: String,
    val overview: String
)