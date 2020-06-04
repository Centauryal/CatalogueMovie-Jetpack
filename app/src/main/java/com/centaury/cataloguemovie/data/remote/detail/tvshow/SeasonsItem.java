package com.centaury.cataloguemovie.data.remote.detail.tvshow;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class SeasonsItem {

    @SerializedName("air_date")
    private String airDate;

    @SerializedName("overview")
    private String overview;

    @SerializedName("episode_count")
    private int episodeCount;

    @SerializedName("name")
    private String name;

    @SerializedName("season_number")
    private int seasonNumber;

    @SerializedName("id")
    private int id;

    @SerializedName("poster_path")
    private String posterPath;

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(int episodeCount) {
        this.episodeCount = episodeCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    @Override
    public String toString() {
        return
                "SeasonsItem{" +
                        "air_date = '" + airDate + '\'' +
                        ",overview = '" + overview + '\'' +
                        ",episode_count = '" + episodeCount + '\'' +
                        ",name = '" + name + '\'' +
                        ",season_number = '" + seasonNumber + '\'' +
                        ",id = '" + id + '\'' +
                        ",poster_path = '" + posterPath + '\'' +
                        "}";
    }
}