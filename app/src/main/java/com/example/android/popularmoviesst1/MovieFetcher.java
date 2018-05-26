package com.example.android.popularmoviesst1;

import android.os.AsyncTask;

import com.example.android.popularmoviesst1.data.MovieJson;
import com.example.android.popularmoviesst1.model.Movie;
import com.example.android.popularmoviesst1.utilities.NetworkUtils;

import java.net.URL;

/**
 * Created by beita on 10/03/2018.
 */

public class MovieFetcher extends AsyncTask<String, Void, Movie[]> {

    private MovieFragment mMovieFragment;

    //Constructor
    public MovieFetcher(MovieFragment movieFragmentInstance) {
        this.mMovieFragment = movieFragmentInstance;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Movie[] doInBackground(String... userSortChoice) {
        URL movieURL = null;

        if (userSortChoice[0] == null)
            movieURL = NetworkUtils.buildURL(mMovieFragment.getActivity());
        else
            movieURL = NetworkUtils.buildURL(userSortChoice[0], mMovieFragment.getActivity());

        try {
            String movieJSONData = NetworkUtils.getResponseFromHttpURL(movieURL);
            return MovieJson.getMovieDataFromJSON(movieJSONData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Movie[] movies) {

        //Set parsed Movie Data
        if (movies != null) {
            mMovieFragment.showMovieDataView();
            mMovieFragment.setMovieData(movies);
            mMovieFragment.setMovieAdapter();
        } else {
            mMovieFragment.showErrorMessageView();
        }
    }
}
