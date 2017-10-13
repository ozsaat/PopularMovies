package com.osaat.popularmovies.data;


import android.net.Uri;
import android.provider.BaseColumns;

public class FavouriteMoviesContract {

    public static final String AUTHORITY = " com.osaat.popularmovies";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_MOVIES = "movies";
    public static final String PATH_MOVIES_ID = "movies/*";


    public static final class FavouriteMoviesEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();

        public static final String TABLE_NAME = "movies";
        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String POSTER_PATH = "posterPath";
        public static final String RELEASE_DATE = "releaseDate";
        public static final String OVERVIEW = "overview";
        public static final String VOTE_AVERAGE = "voteAverage";
        public static final String BACKDROP_PATH = "backdropPath";
    }
}
