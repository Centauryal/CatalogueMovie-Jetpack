package com.centaury.cataloguemovie.data.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static com.centaury.cataloguemovie.data.local.entity.TVShowEntity.TABLE_NAME;

/**
 * Created by Centaury on 10/6/2019.
 */
@Entity(tableName = TABLE_NAME)
public class TVShowEntity {

    public static final String TABLE_NAME = "tvshowentities";
    public static final String COLUMN_TVSHOW_ID = "tvshowId";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ORIGINAL_NAME = "originalName";
    public static final String COLUMN_POSTER = "posterPath";
    public static final String COLUMN_BACKDROP = "backdropPath";
    public static final String COLUMN_GENRE = "genre";
    public static final String COLUMN_RELEASE_DATE = "releaseDate";
    public static final String COLUMN_OVERVIEW = "overview";
    public static final String COLUMN_VOTE_AVERAGE = "voteAverage";

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = COLUMN_TVSHOW_ID)
    private int tvshowId;

    @ColumnInfo(name = COLUMN_NAME)
    private String name;

    @ColumnInfo(name = COLUMN_ORIGINAL_NAME)
    private String originalName;

    @ColumnInfo(name = COLUMN_POSTER)
    private String posterPath;

    @ColumnInfo(name = COLUMN_BACKDROP)
    private String backdropPath;

    @ColumnInfo(name = COLUMN_GENRE)
    private String genres;

    @ColumnInfo(name = COLUMN_RELEASE_DATE)
    private String releaseDate;

    @ColumnInfo(name = COLUMN_OVERVIEW)
    private String overview;

    @ColumnInfo(name = COLUMN_VOTE_AVERAGE)
    private double voteAverage;

    public TVShowEntity(int tvshowId, String name, String originalName, String posterPath, String backdropPath, String genres, String releaseDate, String overview, double voteAverage) {
        this.tvshowId = tvshowId;
        this.name = name;
        this.originalName = originalName;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.genres = genres;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.voteAverage = voteAverage;
    }

    public int getTvshowId() {
        return tvshowId;
    }

    public void setTvshowId(int tvshowId) {
        this.tvshowId = tvshowId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }
}
