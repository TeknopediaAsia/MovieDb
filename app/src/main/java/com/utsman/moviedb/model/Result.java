package com.utsman.moviedb.model;

// "results": [
//    {
//      "vote_average": 7.5,
//      "vote_count": 11658,
//      "id": 284053,
//      "video": false,
//      "media_type": "movie",
//      "title": "Thor: Ragnarok",
//      "popularity": 48.007,
//      "poster_path": "/rzRwTcFvttcN1ZpX2xv4j3tSdJu.jpg",
//      "original_language": "en",
//      "original_title": "Thor: Ragnarok",
//      "genre_ids": [
//        28,
//        12,
//        35,
//        14
//      ],
//      "backdrop_path": "/kaIfm5ryEOwYg8mLbq8HkPuM1Fo.jpg",
//      "adult": false,
//      "overview": "Thor is imprisoned on the other side of the universe and finds himself in a race against time to get back to Asgard to stop Ragnarok, the destruction of his home-world and the end of Asgardian civilization, at the hands of an all-powerful new threat, the ruthless Hela.",
//      "release_date": "2017-10-25"
//    },

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("results")
    private List<Movie> movieList;

    public Result(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
