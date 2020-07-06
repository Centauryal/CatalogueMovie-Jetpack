package com.centaury.domain.movies.model

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
data class Detail(
    private val id: Int,
    private val title: String,
    private val image: String,
    private val imageBackground: String,
    private val genre: String,
    private val vote: String,
    private val rating: Float,
    private val date: String,
    private val overview: String
)