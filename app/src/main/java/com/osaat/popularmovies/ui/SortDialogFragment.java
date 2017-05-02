package com.osaat.popularmovies.ui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.osaat.popularmovies.R;

/**
 * Created by osaat on 21/04/2017.
 */

public class SortDialogFragment extends DialogFragment {

    public SortDialogFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sort, container);
        return view;

    }
}
