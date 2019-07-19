package com.utsman.moviedb.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "item_movie")
public class MovieEntity {
    @PrimaryKey
    private Integer id;
    private String title;
    private String urlImg;

    public MovieEntity(Integer id, String title, String urlImg) {
        this.id = id;
        this.title = title;
        this.urlImg = urlImg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }
}
