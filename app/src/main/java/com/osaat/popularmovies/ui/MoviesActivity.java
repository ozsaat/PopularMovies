package com.osaat.popularmovies.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.osaat.popularmovies.R;
import com.osaat.popularmovies.data.Movie;
import com.osaat.popularmovies.data.MovieDBClient;
import com.osaat.popularmovies.data.MovieResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesActivity extends AppCompatActivity {

    private Context mContext;
    private Activity mActivity;

    private RelativeLayout mRelativeLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MovieAdapter mAdapter;

    private MovieDBClient movieDBClient;
//    private Button dummyButton;
    private List<Movie> mMovie;

    public static final String POSTER_PATH = "http://image.tmdb.org/t/p/w342//";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        mContext = getApplicationContext();
        mActivity = MoviesActivity.this;
        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        dummyButton = (Button) findViewById(R.id.button);

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
//                Context context = getApplicationContext();
//                Class destinationClass = DetailActivity.class;
                Intent intent = new Intent(MoviesActivity.this, DetailActivity.class);
//                Intent intent = new Intent(context, destinationClass);
                intent.putExtra("key", mMovie.get(position));
//                intent.putExtra(Intent.EXTRA_TEXT, position);
                startActivity(intent);
//                Toast.makeText(mContext, "Clicked ", Toast.LENGTH_LONG).show();
            }
        });

//        dummyButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                movieDBClient.getPopularMovies(new MovieDBClient.Listener() {
//
//                    @Override
//                    public void onResponse(MovieResponse movieResponse) {
//                        List<Movie> movies = movieResponse.getResults();
//
////                        List<String> titles = new ArrayList<>();
////                        for (Movie movie : movies) {
////                            titles.add(movie.getTitle());
////
////                        }
//
//                        mAdapter.setData(movies);
//                    }
//
//                    @Override
//                    public void onFailure(Throwable t) {
//                        Toast.makeText(mContext, "Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });


            }
//        });
//    }

//    @Override
//    public void onClick(String weatherForDay) {
//        Context context = this;
//        Class destinationClass = DetailActivity.class;
//        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
//        // COMPLETED (1) Pass the weather to the DetailActivity
//        intentToStartDetailActivity.putExtra(Intent.EXTRA_TEXT, weatherForDay);
//        startActivity(intentToStartDetailActivity);
//    }
}
