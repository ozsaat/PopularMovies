package com.osaat.popularmovies.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.osaat.popularmovies.R;
import com.osaat.popularmovies.data.Movie;
import com.osaat.popularmovies.data.MovieDBClient;
import com.osaat.popularmovies.databinding.ActivityDetailBinding;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        Movie movie = (Movie) intent.getSerializableExtra("key");

        String backdrop = backdropPath(movie.getBackdropPath());

        ImageView backdropPoster = (ImageView) findViewById(R.id.movie_detail_poster);

        Picasso.with(this)
                .load(backdrop)
                .placeholder(R.color.placeholder_background)
                .into(backdropPoster);

        binding.setMovie(movie);

    }

    private String backdropPath(String path) {
        return MovieDBClient.BACKDROP_PATH + path;
    }

}
