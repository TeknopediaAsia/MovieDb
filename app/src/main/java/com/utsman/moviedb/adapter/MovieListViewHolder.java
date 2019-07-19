package com.utsman.moviedb.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.utsman.moviedb.DetailActivity;
import com.utsman.moviedb.R;
import com.utsman.moviedb.model.Movie;

public class MovieListViewHolder extends RecyclerView.ViewHolder {

    public MovieListViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    void bind(final Movie movie) {
        ImageView imgBannerMovie = itemView.findViewById(R.id.img_view_banner_movie);
        TextView txtTitleMovie = itemView.findViewById(R.id.txt_view_title_movie);

        String urlBanner = itemView.getContext().getString(R.string.base_url_image) + movie.getPosterPath();
        Glide.with(itemView.getContext())
                .load(urlBanner)
                .into(imgBannerMovie);

        txtTitleMovie.setText(movie.getTitle());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra("id_movie", movie.getId());
                itemView.getContext().startActivity(intent);
            }
        });
    }
}