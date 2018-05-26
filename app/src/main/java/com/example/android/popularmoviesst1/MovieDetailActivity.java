package com.example.android.popularmoviesst1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.android.popularmoviesst1.model.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {

    //Member Variables
    private final static String PARCELABLE_EXTRA_KEY = "Movie";
    //Bind Views
    @BindView(R.id.thumbnail)
    ImageView mThumbnailImageView;
    @BindView(R.id.movie_title)
    TextView mMovieTitleTextView;
    @BindView(R.id.movie_overview)
    TextView mMovieOverviewTextView;
    @BindView(R.id.tv_movie_vote_average)
    TextView mMovieVoteAverageTextView;
    @BindView(R.id.movie_vote_average_section)
    RatingBar mMovieVoteAverageRatingBar;
    @BindView(R.id.release_date)
    TextView mMovieReleaseDateTextView;

    private Movie currentMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        Intent intent = getIntent();

        ButterKnife.bind(this);

        if (intent != null) {
            if (intent.hasExtra(PARCELABLE_EXTRA_KEY)) {

                currentMovie = intent.getParcelableExtra(PARCELABLE_EXTRA_KEY);

                //Double to Float
                //float floatVoteAverage = Double.valueOf(currentMovie.getMediaVotos()).floatValue();
                float floatVoteAverage = (float) currentMovie.getMediaVotos();

                //Display Movie Details
                Picasso.with(this).load(currentMovie.getPosterURLString()).placeholder(R.mipmap.moviesimg).error(R.mipmap.no_image).into(mThumbnailImageView);
                mMovieTitleTextView.setText(currentMovie.getTitulo());
                mMovieOverviewTextView.setText(currentMovie.getOverview());
                mMovieVoteAverageTextView.setText(Float.toString(floatVoteAverage));
                mMovieVoteAverageRatingBar.setRating(floatVoteAverage);
                mMovieReleaseDateTextView.setText(currentMovie.getFechaLanzamiento());
            }
        }
    }
}