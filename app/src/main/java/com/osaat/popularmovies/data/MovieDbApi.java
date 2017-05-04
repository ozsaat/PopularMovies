package com.osaat.popularmovies.data;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDbApi {

    public static final String API_KEY = "Your Key";
    //TODO add key
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    public static final String GET_POPULAR_MOVIES = "http://api.themoviedb.org/3/discover/movie?language=en&sort_by=popularity.desc&api_key=" + API_KEY;
    public static final String GET_HIGHEST_RATED_MOVIES = "http://api.themoviedb.org/3/discover/movie?vote_count.gte=500&language=en&sort_by=vote_average.desc&api_key=" + API_KEY;
    public static final String GET_TRAILERS = "http://api.themoviedb.org/3/movie/%s/videos?api_key=" + API_KEY;
    public static final String GET_REVIEWS = "http://api.themoviedb.org/3/movie/%s/reviews?api_key=" + API_KEY;
    public static final String POSTER_PATH = "http://image.tmdb.org/t/p/w342//";
    public static final String BACKDROP_PATH = "http://image.tmdb.org/t/p/w780//";
    static final String YOUTUBE_VIDEO_URL = "http://www.youtube.com/watch?v=%1$s";
    static final String YOUTUBE_THUMBNAIL_URL = "http://img.youtube.com/vi/%1$s/0.jpg";


    @GET("discover/movie")
    Call<MovieResponse> getPopularMovies(@Query("language") String language, @Query("sort_by") String sortBy, @Query("api_key") String apiKey);

    @GET("discover/movie")
    Call<MovieResponse> getHighestRatedMovie(@Query("vote_count.gte") String voteCount, @Query("language") String language, @Query("sort_by") String voteAverage, @Query("api_key") String apiKey);

//    class Factory {
//        private static MovieDbApi service;
//        public static MovieDbApi getInstance() {
//            if (service == null) {
//                Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).build();
//                service = retrofit.create(MovieDbApi.class);
//                return service;
//            }
//            return service;
//        }
//    }

}
