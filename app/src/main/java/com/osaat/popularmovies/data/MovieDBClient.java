package com.osaat.popularmovies.data;

import android.util.Log;

import com.osaat.popularmovies.ui.MovieAdapter;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDBClient {

    //TODO add key
    public static final String BACKDROP_PATH = "http://image.tmdb.org/t/p/w780//";
    private static final String API_KEY = "";
    private static final String BASE_URL = "http://api.themoviedb.org/3/";

    public static final String GET_TRAILERS = "http://api.themoviedb.org/3/movie/%s/videos?api_key=" + API_KEY;
    public static final String GET_REVIEWS = "http://api.themoviedb.org/3/movie/%s/reviews?api_key=" + API_KEY;

    private MovieDbApi movieDbApi;
    private Movie movie;

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
        Call<MovieResponse<Movie>> call = movieDbApi.getPopularMovies("en", "popularity.desc", API_KEY);

        call.enqueue(new Callback<MovieResponse<Movie>>() {
            @Override
            public void onResponse(Call<MovieResponse<Movie>> call, Response<MovieResponse<Movie>> response) {
                MovieResponse movieResponse = response.body();

                listener.onResponse(movieResponse);
            }

            @Override
            public void onFailure(Call<MovieResponse<Movie>> call, Throwable t) {
                Log.d("XXX", "error");

                listener.onFailure(t);
            }
        });
    }

    public void getHighestRatedMovies(final Listener listener) {

        Call<MovieResponse<Movie>> call = movieDbApi.getHighestRatedMovie("500", "en", "vote_average.desc", API_KEY);
        call.enqueue(new Callback<MovieResponse<Movie>>() {
            @Override
            public void onResponse(Call<MovieResponse<Movie>> call, Response<MovieResponse<Movie>> response) {
                MovieResponse movieResponse = response.body();

                listener.onResponse(movieResponse);
            }

            @Override
            public void onFailure(Call<MovieResponse<Movie>> call, Throwable t) {
                Log.d("XXX", "error");

                listener.onFailure(t);
            }
        });
    }

    public void getReviewsAndTrailers(final Listener listener, String id) {
        Call<MovieResponse<Reviews>> callReviews = movieDbApi.getMovieReviews(id, API_KEY);
//        Call<MovieResponse> callReviews = movieDbApi.getMovieReviews(movie.getId(), API_KEY);
        callReviews.enqueue(new Callback<MovieResponse<Reviews>>() {
            @Override
            public void onResponse(Call<MovieResponse<Reviews>> call, Response<MovieResponse<Reviews>> response) {
                MovieResponse movieResponse = response.body();

                listener.onResponse(movieResponse);
            }

            @Override
            public void onFailure(Call<MovieResponse<Reviews>> call, Throwable t) {
                Log.d("XXX", "error reviews");

                listener.onFailure(t);

            }
        });
//
//        Call<MovieResponse<Trailers>> callTrailers = movieDbApi.getMovieTrailers(id, API_KEY);
////        Call<MovieResponse> callTrailers = movieDbApi.getMovieTrailers(movie.getId(), API_KEY);
//        callTrailers.enqueue(new Callback<MovieResponse<Trailers>>() {
//            @Override
//            public void onResponse(Call<MovieResponse<Trailers>> call, Response<MovieResponse<Trailers>> response) {
//                MovieResponse movieResponse = response.body();
//
//                listener.onResponse(movieResponse);
//            }
//
//            @Override
//            public void onFailure(Call<MovieResponse<Trailers>> call, Throwable t) {
//                Log.d("XXX", "error trailers");
//            }
//        });
    }

    public interface Listener {

        void onResponse(MovieResponse movieResponse);

        void onFailure(Throwable t);
    }
}
