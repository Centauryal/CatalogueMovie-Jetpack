package com.centaury.domain.detail.model

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
data class Detail(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val image: String,
    val imageBackground: String,
    val genre: List<String>,
    val vote: Double,
    val rating: Double,
    val date: String,
    val overview: String
)