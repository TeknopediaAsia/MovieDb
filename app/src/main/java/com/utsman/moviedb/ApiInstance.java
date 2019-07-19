package com.utsman.moviedb;

import com.utsman.moviedb.model.Movie;
import com.utsman.moviedb.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInstance {

    @GET("trending/movie/week")
    Call<Result> getList(@Query("api_key") String apiKey);

    @GET("movie/{id_movie}")
    Call<Movie> getMovie(@Path("id_movie") Integer idMovie,
                         @Query("api_key") String apiKey);
}
