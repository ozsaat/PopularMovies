package com.osaat.popularmovies.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.osaat.popularmovies.data.FavouriteMoviesContract.FavouriteMoviesEntry.*;

public class FavouritesDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favourites.db";
    private static final int DATABASE_VERSION = 1;

    public FavouritesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_FAVOURIRTES_TABLE = "CREATE_TABLE " +
                TABLE_NAME + "(" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ID + " INTEGER NOT NULL," +
                TITLE + " TEXT NOT NULL," +
                POSTER_PATH + " TEXT NOT NULL," +
                RELEASE_DATE + " TEXT NOT NULL," +
                OVERVIEW + " TEXT NOT NULL," +
                VOTE_AVERAGE + " DOUBLE NOT NULL" +
                BACKDROP_PATH + " TEXT NOT NULL" + ");";

        db.execSQL(SQL_CREATE_FAVOURIRTES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
