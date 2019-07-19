package com.utsman.moviedb;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.utsman.moviedb.adapter.MovieListAdapter;
import com.utsman.moviedb.model.Movie;
import com.utsman.moviedb.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(getString(R.string.base_url))
                .build();

        ApiInstance apiInstance = retrofit.create(ApiInstance.class);

        apiInstance.getList(getString(R.string.api_key_movie))
                .enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        List<Movie> movieList = response.body().getMovieList();
                        Log.i("My retrofit", "Jumlah data = " + movieList.size());
                        setupRecyclerView(movieList);
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        Log.e("My retrofit", "error" + t.getLocalizedMessage());
                    }
                });
    }

    private void setupRecyclerView(List<Movie> movieList) {
        RecyclerView recyclerViewMovie = findViewById(R.id.recycler_movie);
        MovieListAdapter movieListAdapter = new MovieListAdapter(movieList);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerViewMovie.setLayoutManager(gridLayoutManager);
        recyclerViewMovie.setAdapter(movieListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_save) {
            Intent intent = new Intent(this, SavedActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
