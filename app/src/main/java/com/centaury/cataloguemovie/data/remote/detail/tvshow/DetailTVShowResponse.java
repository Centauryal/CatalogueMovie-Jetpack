package com.centaury.cataloguemovie.data.remote.detail.tvshow;

import com.centaury.cataloguemovie.data.remote.genre.GenresItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class DetailTVShowResponse {

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("number_of_episodes")
    private int numberOfEpisodes;

    @SerializedName("networks")
    private List<NetworksItem> networks;

    @SerializedName("type")
    private String type;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("genres")
    private List<GenresItem> genres;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("id")
    private int id;

    @SerializedName("number_of_seasons")
    private int numberOfSeasons;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("first_air_date")
    private String firstAirDate;

    @SerializedName("overview")
    private String overview;

    @SerializedName("seasons")
    private List<SeasonsItem> seasons;

    @SerializedName("languages")
    private List<String> languages;

    @SerializedName("created_by")
    private List<CreatedByItem> createdBy;

    @SerializedName("last_episode_to_air")
    private LastEpisodeToAir lastEpisodeToAir;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("origin_country")
    private List<String> originCountry;

    @SerializedName("production_companies")
    private List<ProductionCompaniesItem> productionCompanies;

    @SerializedName("original_name")
    private String originalName;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("name")
    private String name;

    @SerializedName("episode_run_time")
    private List<Integer> episodeRunTime;

    @SerializedName("next_episode_to_air")
    private NextEpisodeToAir nextEpisodeToAir;

    @SerializedName("in_production")
    private boolean inProduction;

    @SerializedName("last_air_date")
    private String lastAirDate;

    @SerializedName("homepage")
    private String homepage;

    @SerializedName("status")
    private String status;

    public DetailTVShowResponse(int id, String name, String posterPath, String firstAirDate, String overview, double voteAverage) {
        this.id = id;
        this.name = name;
        this.posterPath = posterPath;
        this.firstAirDate = firstAirDate;
        this.overview = overview;
        this.voteAverage = voteAverage;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public List<NetworksItem> getNetworks() {
        return networks;
    }

    public void setNetworks(List<NetworksItem> networks) {
        this.networks = networks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public List<GenresItem> getGenres() {
        return genres;
    }

    public void setGenres(List<GenresItem> genres) {
        this.genres = genres;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<SeasonsItem> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<SeasonsItem> seasons) {
        this.seasons = seasons;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<CreatedByItem> getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(List<CreatedByItem> createdBy) {
        this.createdBy = createdBy;
    }

    public LastEpisodeToAir getLastEpisodeToAir() {
        return lastEpisodeToAir;
    }

    public void setLastEpisodeToAir(LastEpisodeToAir lastEpisodeToAir) {
        this.lastEpisodeToAir = lastEpisodeToAir;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    public List<ProductionCompaniesItem> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompaniesItem> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
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

    public List<Integer> getEpisodeRunTime() {
        return episodeRunTime;
    }

    public void setEpisodeRunTime(List<Integer> episodeRunTime) {
        this.episodeRunTime = episodeRunTime;
    }

    public NextEpisodeToAir getNextEpisodeToAir() {
        return nextEpisodeToAir;
    }

    public void setNextEpisodeToAir(NextEpisodeToAir nextEpisodeToAir) {
        this.nextEpisodeToAir = nextEpisodeToAir;
    }

    public boolean isInProduction() {
        return inProduction;
    }

    public void setInProduction(boolean inProduction) {
        this.inProduction = inProduction;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return
                "DetailTVShowResponse{" +
                        "original_language = '" + originalLanguage + '\'' +
                        ",number_of_episodes = '" + numberOfEpisodes + '\'' +
                        ",networks = '" + networks + '\'' +
                        ",type = '" + type + '\'' +
                        ",backdrop_path = '" + backdropPath + '\'' +
                        ",genres = '" + genres + '\'' +
                        ",popularity = '" + popularity + '\'' +
                        ",id = '" + id + '\'' +
                        ",number_of_seasons = '" + numberOfSeasons + '\'' +
                        ",vote_count = '" + voteCount + '\'' +
                        ",first_air_date = '" + firstAirDate + '\'' +
                        ",overview = '" + overview + '\'' +
                        ",seasons = '" + seasons + '\'' +
                        ",languages = '" + languages + '\'' +
                        ",created_by = '" + createdBy + '\'' +
                        ",last_episode_to_air = '" + lastEpisodeToAir + '\'' +
                        ",poster_path = '" + posterPath + '\'' +
                        ",origin_country = '" + originCountry + '\'' +
                        ",production_companies = '" + productionCompanies + '\'' +
                        ",original_name = '" + originalName + '\'' +
                        ",vote_average = '" + voteAverage + '\'' +
                        ",name = '" + name + '\'' +
                        ",episode_run_time = '" + episodeRunTime + '\'' +
                        ",next_episode_to_air = '" + nextEpisodeToAir + '\'' +
                        ",in_production = '" + inProduction + '\'' +
                        ",last_air_date = '" + lastAirDate + '\'' +
                        ",homepage = '" + homepage + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}