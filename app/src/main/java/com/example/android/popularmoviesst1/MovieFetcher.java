/*
 *
 *  * PROJECT LICENSE
 *  *
 *  * This project was submitted by Beatriz Ovejero as part of the Android Developer
 *  * Nanodegree at Udacity.
 *  *
 *  * As part of Udacity Honor code, your submissions must be your own work, hence
 *  * submitting this project as yours will cause you to break the Udacity Honor Code
 *  * and the suspension of your account.
 *  *
 *  * As author of the project, I allow you to check it as a reference, but if you submit it
 *  * as your own project, it's your own responsibility if you get expelled.
 *  *
 *  * Copyright (c) 2018 Beatriz Ovejero
 *  *
 *  * Besides the above notice, the following license applies and this license notice must be
 *  * included in all works derived from this project.
 *  *
 *  * MIT License
 *  *
 *  * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  * of this software and associated documentation files (the "Software"), to deal
 *  * in the Software without restriction, including without limitation the rights
 *  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  * copies of the Software, and to permit persons to whom the Software is
 *  * furnished to do so, subject to the following conditions:
 *  *
 *  * The above copyright notice and this permission notice shall be included in all
 *  * copies or substantial portions of the Software.
 *  *
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  * SOFTWARE.
 *
 */

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
