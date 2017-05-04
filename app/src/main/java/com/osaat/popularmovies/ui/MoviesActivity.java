package com.osaat.popularmovies.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.osaat.popularmovies.R;
import com.osaat.popularmovies.data.Movie;
import com.osaat.popularmovies.data.MovieDBClient;
import com.osaat.popularmovies.data.MovieResponse;

import java.util.ArrayList;
import java.util.List;

public class MoviesActivity extends AppCompatActivity {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MovieAdapter mAdapter;
    private MovieDBClient movieDBClient;
    private List<Movie> mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        mContext = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mLayoutManager = new GridLayoutManager(mContext, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        movieDBClient = new MovieDBClient();


        mAdapter = new MovieAdapter(mContext, new ArrayList<Movie>());
        mRecyclerView.setAdapter(mAdapter);

        movieDBClient.getPopularMovies(new MovieDBClient.Listener() {

            @Override
            public void onResponse(MovieResponse movieResponse) {
                List<Movie> movies = movieResponse.getResults();
                mMovie = movies;

                mAdapter.setData(movies);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(mContext, "Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter.setListener(new MovieAdapter.Listener() {

            @Override
            public void onClick(int position) {
                Intent intent = new Intent(MoviesActivity.this, DetailActivity.class);
                intent.putExtra("key", mMovie.get(position));
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.popular:
                item.setChecked(true);
                movieDBClient.getPopularMovies(new MovieDBClient.Listener() {

                    @Override
                    public void onResponse(MovieResponse movieResponse) {
                        List<Movie> movies = movieResponse.getResults();
                        mMovie = movies;

                        mAdapter.setData(movies);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(mContext, "Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.highest:
                item.setChecked(true);
                movieDBClient.getHighestRatedMovies(new MovieDBClient.Listener() {

                    @Override
                    public void onResponse(MovieResponse movieResponse) {
                        List<Movie> movies = movieResponse.getResults();
                        mMovie = movies;

                        mAdapter.setData(movies);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(mContext, "Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;

        }
        return true;
    }
}
