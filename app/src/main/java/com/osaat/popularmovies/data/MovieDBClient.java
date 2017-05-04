package com.osaat.popularmovies.data;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by osaat on 28/03/2017.
 */

public class MovieDBClient {

    //TODO add key
    public static final String BACKDROP_PATH = "http://image.tmdb.org/t/p/w780//";
    private static final String API_KEY = "Api Key";
    private static final String BASE_URL = "http://api.themoviedb.org/3/";
    private MovieDbApi movieDbApi;

    public MovieDBClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build();

        movieDbApi = retrofit.create(MovieDbApi.class);
    }

    public void getPopularMovies(final Listener listener) {
        Call<MovieResponse> call = movieDbApi.getPopularMovies("en", "popularity.desc", API_KEY);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();

                listener.onResponse(movieResponse);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d("XXX", "error");

                listener.onFailure(t);
            }
        });
    }

    public void getHighestRatedMovies(final Listener listener) {

        Call<MovieResponse> call = movieDbApi.getHighestRatedMovie("500", "en", "vote_average.desc", API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();

                listener.onResponse(movieResponse);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d("XXX", "error");

                listener.onFailure(t);
            }
        });
    }

    public interface Listener {

        void onResponse(MovieResponse movieResponse);

        void onFailure(Throwable t);
    }
}
