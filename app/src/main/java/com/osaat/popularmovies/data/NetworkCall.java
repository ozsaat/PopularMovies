package com.osaat.popularmovies.data;

import android.os.AsyncTask;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by osaat on 28/03/2017.
 */

public class NetworkCall extends AsyncTask<Call, Void, Object> {

    @Override
    protected Object doInBackground(Call... params) {
        try {
            Call call = params[0];
            Response<Object> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object result) {
        Timber.d(result.toString());
    }
}

