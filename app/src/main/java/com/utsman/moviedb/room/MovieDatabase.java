package com.utsman.moviedb.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.utsman.moviedb.model.MovieEntity;

@Database(entities = {MovieEntity.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDao getMovieDao();

    public static MovieDatabase instance(Context context) {
        return Room.databaseBuilder(context, MovieDatabase.class, "movieDb")
                .allowMainThreadQueries()
                .build();
    }
}