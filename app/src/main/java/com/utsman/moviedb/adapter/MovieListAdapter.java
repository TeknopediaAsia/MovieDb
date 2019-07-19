package com.utsman.moviedb.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.utsman.moviedb.R;
import com.utsman.moviedb.model.Movie;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListViewHolder> {

    private List<Movie> movieList;

    public MovieListAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new MovieListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder movieListViewHolder, int i) {
        Movie movie = movieList.get(i);
        movieListViewHolder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}