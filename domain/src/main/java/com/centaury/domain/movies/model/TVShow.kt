package com.centaury.domain.movies.model

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
data class TVShow(
    private val id: Int,
    private val title: String,
    private val titleBackground: String,
    private val image: String,
    private val genre: String,
    private val overview: String,
    private val date: String
)