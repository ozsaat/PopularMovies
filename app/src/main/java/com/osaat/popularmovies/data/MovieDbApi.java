package com.osaat.popularmovies.data;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDbApi {

    @GET("discover/movie")
    Call<MovieResponse<Movie>> getPopularMovies(@Query("language") String language, @Query("sort_by") String sortBy, @Query("api_key") String apiKey);

    @GET("discover/movie")
    Call<MovieResponse<Movie>> getHighestRatedMovie(@Query("vote_count.gte") String voteCount, @Query("language") String language, @Query("sort_by") String voteAverage, @Query("api_key") String apiKey);

    @GET("/3/movie/{id}/reviews")
    Call<MovieResponse<Reviews>> getMovieReviews(@Path("id") String id, @Query("api_key") String apiKey);
    // not sure if int id will work here. It may need to be String

    @GET("/3/movie/{id}/videos")
    Call<MovieResponse<Trailers>> getMovieTrailers(@Path("id") String id, @Query("api_key") String apiKey);
}
