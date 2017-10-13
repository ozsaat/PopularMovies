package com.osaat.popularmovies.ui;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.osaat.popularmovies.R;
import com.osaat.popularmovies.data.Movie;
import com.osaat.popularmovies.data.MovieDBClient;
import com.osaat.popularmovies.data.MovieResponse;
import com.osaat.popularmovies.data.Reviews;
import com.osaat.popularmovies.data.Trailers;
import com.osaat.popularmovies.databinding.ActivityDetailBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private Menu menu;
    private boolean isFavorited;
    private Movie movie;
    private MenuItem favMenuItem;
    private List<Reviews> reviews;
    private List<Trailers> trailers;
    private Context context;
    private MovieDBClient movieDBClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        movie = (Movie) intent.getSerializableExtra("key");

        String backdrop = backdropPath(movie.getBackdropPath());

        ImageView backdropPoster = (ImageView) findViewById(R.id.movie_detail_poster);

        Picasso.with(this)
                .load(backdrop)
                .placeholder(R.color.placeholder_background)
                .into(backdropPoster);

        binding.setMovie(movie);

        movieDBClient = new MovieDBClient();

        movieDBClient.getReviewsAndTrailers(new MovieDBClient.Listener() {
            @Override
            public void onResponse(MovieResponse movieResponse) {
                List<Reviews> movieReview = movieResponse.getResults();
                reviews = movieReview;
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(context, "Error " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        }, movie.getId());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_favorite, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        favMenuItem = menu.findItem(R.id.favorite);
        favMenuItem.setIcon(getFavIcon(isFavorited));

//        if (isFavorited) {
//            menu.findItem(R.id.favorite).setVisible(false);
//            menu.findItem(R.id.unfavorite).setVisible(true);
//        } else {
//            menu.findItem(R.id.favorite).setVisible(true);
//            menu.findItem(R.id.unfavorite).setVisible(false);
//        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.favorite:
                isFavorited = !isFavorited;

                favMenuItem.setIcon(getFavIcon(isFavorited));
                invalidateOptionsMenu();
                updateFavsDB(null);
                return true;

//            case R.id.unfavorite:
//                isFavorited = !isFavorited;
//                invalidateOptionsMenu();
//                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Drawable getFavIcon(boolean isFavorited) {
        Drawable favIcon;
        if (isFavorited) {
            favIcon = getResources().getDrawable(R.mipmap.ic_favorite);
        } else {
            favIcon = getResources().getDrawable(R.mipmap.ic_unfavorite);
        }
        return favIcon;
    }

    private void updateFavsDB(Movie movie) {

    }

    private String backdropPath(String path) {
        return MovieDBClient.BACKDROP_PATH + path;
    }

}
