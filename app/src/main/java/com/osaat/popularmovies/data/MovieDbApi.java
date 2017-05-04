package com.osaat.popularmovies.data;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDbApi {

    @GET("discover/movie")
    Call<MovieResponse> getPopularMovies(@Query("language") String language, @Query("sort_by") String sortBy, @Query("api_key") String apiKey);

    @GET("discover/movie")
    Call<MovieResponse> getHighestRatedMovie(@Query("vote_count.gte") String voteCount, @Query("language") String language, @Query("sort_by") String voteAverage, @Query("api_key") String apiKey);

}
