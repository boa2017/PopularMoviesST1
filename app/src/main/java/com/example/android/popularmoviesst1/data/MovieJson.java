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

package com.example.android.popularmoviesst1.data;

import com.example.android.popularmoviesst1.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by beita on 10/03/2018.
 */

public class MovieJson {

    public static Movie[] getMovieDataFromJSON(String movieJSONData) throws JSONException {

        final String MOVIE_ID = "id";
        final String MOVIE_TITLE = "title";
        final String MOVIE_POPULARITY = "popularity";
        final String MOVIE_OVERVIEW = "overview";
        final String MOVIE_RELEASE_DATE = "release_date";
        final String MOVIE_OUTPUT = "results";
        final String MOVIE_VOTE_COUNT = "vote_count";
        final String MOVIE_VOTE_AVERAGE = "vote_average";
        final String MOVIE_POSTER_PATH = "poster_path";
        final String MOVIE_BACKDROP_PATH = "backdrop_path";

        //Movie Data
        Movie[] movies;

        //Convert API Result in JSON Object
        JSONObject billboardJSON = new JSONObject(movieJSONData);

        JSONArray movieArray = billboardJSON.getJSONArray(MOVIE_OUTPUT);
        movies = new Movie[movieArray.length()];

        //Loop through JSON Array
        for (int pos = 0; pos < movieArray.length(); pos++) {
            JSONObject movieInfo = movieArray.getJSONObject(pos);
            Movie movieData = new Movie(movieInfo.getInt(MOVIE_VOTE_COUNT),
                    movieInfo.getInt(MOVIE_ID),
                    movieInfo.getDouble(MOVIE_VOTE_AVERAGE),
                    movieInfo.getString(MOVIE_TITLE),
                    movieInfo.getDouble(MOVIE_POPULARITY),
                    movieInfo.getString(MOVIE_POSTER_PATH),
                    movieInfo.getString(MOVIE_BACKDROP_PATH),
                    movieInfo.getString(MOVIE_OVERVIEW),
                    movieInfo.getString(MOVIE_RELEASE_DATE));

            movies[pos] = movieData;
        }
        return movies;
    }
}
