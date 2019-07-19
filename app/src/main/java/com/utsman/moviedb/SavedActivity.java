package com.utsman.moviedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.utsman.moviedb.adapter.MovieListRoomAdapter;
import com.utsman.moviedb.model.MovieEntity;
import com.utsman.moviedb.room.MovieDatabase;

import java.util.ArrayList;
import java.util.List;

public class SavedActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1010;
    private MovieListRoomAdapter movieListRoomAdapter;
    private MovieDatabase movieDatabase;
    private List<MovieEntity> movieList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieDatabase = MovieDatabase.instance(this);

        List<MovieEntity> movieEntities = movieDatabase.getMovieDao().getListMovie();
        movieList.addAll(movieEntities);

        setupRecyclerView(movieList);
    }

    private void setupRecyclerView(List<MovieEntity> entityList) {
        RecyclerView recyclerViewMovie = findViewById(R.id.recycler_movie);
        movieListRoomAdapter = new MovieListRoomAdapter(entityList);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerViewMovie.setLayoutManager(gridLayoutManager);
        recyclerViewMovie.setAdapter(movieListRoomAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE) {

            movieList.clear();
            movieListRoomAdapter.notifyDataSetChanged();

            List<MovieEntity> newMovieEntities = movieDatabase.getMovieDao().getListMovie();
            movieList.addAll(newMovieEntities);
            movieListRoomAdapter.notifyDataSetChanged();
        }
    }
}
