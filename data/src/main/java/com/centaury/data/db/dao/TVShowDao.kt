package com.centaury.data.db.dao

import androidx.room.*
import com.centaury.data.tvshow.repository.source.local.entity.TVShowEntity
import com.centaury.data.tvshow.repository.source.local.entity.TVShowEntity.Companion.COLUMN_TV_SHOW_ID
import com.centaury.data.tvshow.repository.source.local.entity.TVShowEntity.Companion.TABLE_NAME_TV_SHOW
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/24/2020.
 */
@Dao
interface TVShowDao {

    @Query("SELECT * FROM $TABLE_NAME_TV_SHOW")
    fun loadAllTVShow(): Flowable<List<TVShowEntity>>

    @Transaction
    @Query("SELECT * FROM $TABLE_NAME_TV_SHOW WHERE $COLUMN_TV_SHOW_ID = :id")
    fun loadMovieById(id: Int): Flowable<TVShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTVShow(vararg tvShowEntity: TVShowEntity): Completable

    @Delete
    fun deleteTVShow(vararg tvShowEntity: TVShowEntity): Completable
}