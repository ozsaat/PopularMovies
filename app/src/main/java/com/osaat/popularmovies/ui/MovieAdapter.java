package com.osaat.popularmovies.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.osaat.popularmovies.R;
import com.osaat.popularmovies.data.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by osaat on 16/03/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context mContext;
    private List<Movie> mDataSet;
    public static final String POSTER_PATH = "http://image.tmdb.org/t/p/w342//";
    Listener mListener;

    public MovieAdapter(Context context, List<Movie> list) {
//                                        List<String> list
        mContext = context;
        mDataSet = list;

    }

    public static interface Listener {
        void onClick(int position);
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.custom_view,parent,false);

//        final TextView movieTitle = (TextView) v.findViewById(R.id.movieTitle);
//        final ImageView moviePoster = (ImageView) v.findViewById(R.id.moviePoster);
//        v.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                final String movie = movieTitle.getText().toString();
//
//
////                Toast.makeText(mContext, "Clicked ", Toast.LENGTH_LONG).show();
//            }
//        });

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        String poster = POSTER_PATH + mDataSet.get(position).getPosterPath();
        Picasso.with(mContext)
                .load(poster)
                .placeholder(R.color.placeholder_background)
                .into(holder.imageView);
//        String movie = mDataSet.get(position).getTitle();
//        holder.movieTitle.setText(movie);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(position);
                }
            }
        });
    }

    public void setData(@NonNull List<Movie> movies) {
//                                List<String> titles
        mDataSet = movies;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        @BindView(R.id.moviePoster)
        ImageView imageView;
//        private ImageView imageView;
//        private TextView movieTitle;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
//            imageView = (ImageView) itemView.findViewById(R.id.moviePoster);
//            movieTitle = (TextView) itemView.findViewById(R.id.movieTitle);

        }
    }
}
