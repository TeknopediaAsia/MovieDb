package com.utsman.moviedb.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.utsman.moviedb.R;
import com.utsman.moviedb.model.MovieEntity;

import java.util.List;

public class MovieListRoomAdapter extends RecyclerView.Adapter<MovieListRoomViewHolder> {

    private List<MovieEntity> entityList;

    public MovieListRoomAdapter(List<MovieEntity> entityList) {
        this.entityList = entityList;
    }

    @NonNull
    @Override
    public MovieListRoomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new MovieListRoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListRoomViewHolder movieListRoomViewHolder, int i) {
        MovieEntity entity = entityList.get(i);
        movieListRoomViewHolder.bind(entity);
    }

    @Override
    public int getItemCount() {
        return entityList.size();
    }
}
