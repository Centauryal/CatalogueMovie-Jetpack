package com.centaury.cataloguemovie.data.local.entity;

/**
 * Created by Centaury on 10/6/2019.
 */
public class MovieEntity {

    private int movieId;
    private String name;
    private String imagePath;
    private String date;
    private String desc;
    private String rating;

    public MovieEntity(int movieId, String name, String imagePath, String date, String desc, String rating) {
        this.movieId = movieId;
        this.name = name;
        this.imagePath = imagePath;
        this.date = date;
        this.desc = desc;
        this.rating = rating;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
