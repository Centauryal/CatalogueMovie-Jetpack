package com.centaury.cataloguemovie.data;

/**
 * Created by Centaury on 10/6/2019.
 */
public class TVShowEntity {

    private int tvshowId;
    private String name;
    private String imagePath;
    private String date;
    private String desc;
    private String rating;

    public TVShowEntity(int tvshowId, String name, String imagePath, String date, String desc, String rating) {
        this.tvshowId = tvshowId;
        this.name = name;
        this.imagePath = imagePath;
        this.date = date;
        this.desc = desc;
        this.rating = rating;
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
