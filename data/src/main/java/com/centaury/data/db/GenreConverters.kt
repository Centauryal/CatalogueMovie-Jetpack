package com.centaury.data.db

import androidx.room.TypeConverter

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/29/2020.
 */
class GenreConverters {

    @TypeConverter
    fun toListOfStrings(flatStringList: String): List<Int> {
        val list = mutableListOf<Int>()
        val array = flatStringList.split(",".toRegex()).dropLastWhile {
            it.isEmpty()
        }.toTypedArray()

        for (s in array) {
            if (s.isNotEmpty()) {
                list.add(s.toInt())
            }
        }

        return list
    }

    @TypeConverter
    fun fromListOfStrings(list: List<Int>): String {
        var genreId = ""

        for (i in list) genreId += ",$i"
        return genreId
    }
}