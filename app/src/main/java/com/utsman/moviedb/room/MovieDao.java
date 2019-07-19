package com.utsman.moviedb.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.utsman.moviedb.model.MovieEntity;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(MovieEntity... movieEntities);

    @Delete
    public void delete(MovieEntity... movieEntities);

    @Query("SELECT * FROM item_movie")
    public List<MovieEntity> getListMovie();

    @Query("SELECT * FROM item_movie WHERE id = :id")
    public MovieEntity getMovieDb(Integer id);

}