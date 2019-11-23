package com.centaury.cataloguemovie.data.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static com.centaury.cataloguemovie.data.local.entity.GenreTVShowEntity.TABLE_NAME;

/**
 * Created by Centaury on 11/22/2019.
 */
@Entity(tableName = TABLE_NAME)
public class GenreTVShowEntity {

    public static final String TABLE_NAME = "genretvshow";
    public static final String COLUMN_GENRE_ID = "genreId";
    public static final String COLUMN_NAME = "name";

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = COLUMN_GENRE_ID)
    private int genreId;

    @ColumnInfo(name = COLUMN_NAME)
    private String name;

    public GenreTVShowEntity(int genreId, String name) {
        this.genreId = genreId;
        this.name = name;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
