package com.utsman.moviedb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.utsman.moviedb.model.Movie;
import com.utsman.moviedb.model.MovieEntity;
import com.utsman.moviedb.room.MovieDao;
import com.utsman.moviedb.room.MovieDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setVisibleContent(false);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Integer idMovie = getIntent().getIntExtra("id_movie", 0);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(getString(R.string.base_url))
                .build();

        ApiInstance apiInstance = retrofit.create(ApiInstance.class);
        apiInstance.getMovie(idMovie, getString(R.string.api_key_movie))
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        Movie movie = response.body();
                        setupDetailMovie(movie);
                        setupDatabase(movie);
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {

                    }
                });
    }

    private void setupDetailMovie(Movie movie) {
        TextView txtTitle = findViewById(R.id.txt_view_title_movie);
        TextView txtRating = findViewById(R.id.txt_view_rating);
        TextView txtGenre = findViewById(R.id.txt_view_genre);
        TextView txtRelease = findViewById(R.id.txt_view_release_date);
        TextView txtOverView = findViewById(R.id.txt_view_over_view);
        ImageView imgBackdrop = findViewById(R.id.img_view_backdrop);

        txtTitle.setText(movie.getOriginalTitle());
        txtRating.setText(String.valueOf(movie.getVoteAverage()));
        txtGenre.setText(movie.getGenres().get(0).getName());
        txtRelease.setText(movie.getReleaseDate());
        txtOverView.setText(movie.getOverView());

        String backDropUrl = getString(R.string.base_url_image) + movie.getBackdropPath();
        Glide.with(this).load(backDropUrl).into(imgBackdrop);
        setVisibleContent(true);
    }

    private void setupDatabase(final Movie movie) {
        final FloatingActionButton fabSave = findViewById(R.id.btn_save);
        final MovieEntity movieEntity = new MovieEntity(movie.getId(), movie.getOriginalTitle(), movie.getBackdropPath());

        MovieDatabase movieDatabase = MovieDatabase.instance(this);
        final MovieDao movieDao = movieDatabase.getMovieDao();

        final MovieEntity entityExist = getMovieDb(movieDao, movie);

        final int resImg = resImgBtn(entityExist);
        fabSave.setImageResource(resImg);

        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MovieEntity newEntityExist = getMovieDb(movieDao, movie);

                if (newEntityExist != null) {
                    movieDao.delete(movieEntity);
                    fabSave.setImageResource(R.drawable.ic_bookmark_border);
                } else {
                    movieDao.insert(movieEntity);
                    fabSave.setImageResource(R.drawable.ic_bookmark);
                }
            }
        });
    }

    private MovieEntity getMovieDb(MovieDao movieDao, Movie movie) {
        return movieDao.getMovieDb(movie.getId());
    }

    private int resImgBtn(MovieEntity entityExist) {
        int resId;

        if (entityExist != null) {
            resId = R.drawable.ic_bookmark;
        } else  {
            resId = R.drawable.ic_bookmark_border;
        }

        return resId;
    }

    private void setVisibleContent(Boolean visible) {
        ProgressBar progressBar = findViewById(R.id.progress_circular);
        NestedScrollView parentLayout = findViewById(R.id.parent_layout);

        if (visible) {
            progressBar.setVisibility(View.INVISIBLE);
            parentLayout.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.VISIBLE);
            parentLayout.setVisibility(View.INVISIBLE);
        }
    }
}
