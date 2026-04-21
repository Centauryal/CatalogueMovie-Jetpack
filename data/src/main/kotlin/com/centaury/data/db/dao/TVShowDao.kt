package com.centaury.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.centaury.data.tvshow.repository.source.local.entity.TVShowEntity
import com.centaury.data.tvshow.repository.source.local.entity.TVShowEntity.Companion.COLUMN_TV_SHOW_ID
import com.centaury.data.tvshow.repository.source.local.entity.TVShowEntity.Companion.TABLE_NAME_TV_SHOW
import kotlinx.coroutines.flow.Flow

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/24/2020.
 */
@Dao
interface TVShowDao {

    @Query("SELECT * FROM $TABLE_NAME_TV_SHOW")
    fun loadAllTVShow(): PagingSource<Int, TVShowEntity>

    @Transaction
    @Query("SELECT * FROM $TABLE_NAME_TV_SHOW WHERE $COLUMN_TV_SHOW_ID = :id")
    fun loadMovieById(id: Int): Flow<TVShowEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVShow(vararg tvShowEntity: TVShowEntity)

    @Delete
    suspend fun deleteTVShow(vararg tvShowEntity: TVShowEntity)
}