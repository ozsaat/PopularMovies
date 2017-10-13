package com.osaat.popularmovies.data;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.osaat.popularmovies.BuildConfig;

public class FavouritesContentProvider extends ContentProvider {

    private static String TABLE_FAVOURITES = "favourites";
    private final static int MOVIES = 100;
    private final static int MOVIE_ID = 101;

    public final static UriMatcher URI_MATCHER = uriMatcher();

    private FavouritesDbHelper favouritesDbHelper;

    public static UriMatcher uriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(FavouriteMoviesContract.AUTHORITY, FavouriteMoviesContract.PATH_MOVIES, MOVIES);
        uriMatcher.addURI(FavouriteMoviesContract.AUTHORITY, FavouriteMoviesContract.PATH_MOVIES_ID + "/#", MOVIE_ID);

        return uriMatcher;
    }

    protected  String getAuthority() {
        return BuildConfig.APPLICATION_ID + ".data." + getClass().getSimpleName();
    }

    @Override
    public boolean onCreate() {

        Context context = getContext();
        favouritesDbHelper = new FavouritesDbHelper(context);

        return true;
    }

    @NonNull
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase db = favouritesDbHelper.getWritableDatabase();
        final int match = URI_MATCHER.match(uri);

        Cursor query;
        switch (match) {
            case MOVIES: {
                query = db.query(FavouriteMoviesContract.FavouriteMoviesEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            default: {
                throw new UnsupportedOperationException("Unknown insert uri: " + uri);
            }
        }
        query.setNotificationUri(getContext().getContentResolver(),uri);
        return query;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        return "vnd.android.cursor.dir/vnd." + BuildConfig.APPLICATION_ID + "." + TABLE_FAVOURITES;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
