package com.osaat.popularmovies;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by osaat on 28/03/2017.
 */

public class MoviesApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
    }
}
