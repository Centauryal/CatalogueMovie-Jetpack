package com.centaury.cataloguemovie.data.remote.detail.tvshow;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class LastEpisodeToAir {

    @SerializedName("production_code")
    private String productionCode;

    @SerializedName("air_date")
    private String airDate;

    @SerializedName("overview")
    private String overview;

    @SerializedName("episode_number")
    private int episodeNumber;

    @SerializedName("show_id")
    private int showId;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("name")
    private String name;

    @SerializedName("season_number")
    private int seasonNumber;

    @SerializedName("id")
    private int id;

    @SerializedName("still_path")
    private String stillPath;

    @SerializedName("vote_count")
    private int voteCount;

    public String getProductionCode() {
        return productionCode;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
    }

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

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
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

    public String getStillPath() {
        return stillPath;
    }

    public void setStillPath(String stillPath) {
        this.stillPath = stillPath;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public String toString() {
        return
                "LastEpisodeToAir{" +
                        "production_code = '" + productionCode + '\'' +
                        ",air_date = '" + airDate + '\'' +
                        ",overview = '" + overview + '\'' +
                        ",episode_number = '" + episodeNumber + '\'' +
                        ",show_id = '" + showId + '\'' +
                        ",vote_average = '" + voteAverage + '\'' +
                        ",name = '" + name + '\'' +
                        ",season_number = '" + seasonNumber + '\'' +
                        ",id = '" + id + '\'' +
                        ",still_path = '" + stillPath + '\'' +
                        ",vote_count = '" + voteCount + '\'' +
                        "}";
    }
}