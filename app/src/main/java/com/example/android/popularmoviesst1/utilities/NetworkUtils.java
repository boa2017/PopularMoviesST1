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

package com.example.android.popularmoviesst1.utilities;

import android.content.Context;
import android.net.Uri;

import com.example.android.popularmoviesst1.BuildConfig;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by beita on 10/03/2018.
 */

public final class NetworkUtils {

    private final static String MOVIE_BASE_URL = "http://api.themoviedb.org/3/movie";
    private final static String MOVIE_POPULAR = "popular";
    private final static String API_PARAM_NAME = "api_key";
    private final static String MOVIE_POSTER_BASE_URL = "http://image.tmdb.org/t/p";
    private final static String MOVIE_POSTER_SIZE = "w185";

    //Method to return Poster URL
    public static URL buildPosterURL(String posterPath) {
        Uri builtUri = Uri.parse(MOVIE_POSTER_BASE_URL).buildUpon()
                .appendPath(MOVIE_POSTER_SIZE)
                .appendEncodedPath(posterPath).build();
        URL builtURL = null;

        try {
            builtURL = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return builtURL;
    }

    //Method to return appropriate Poster URL
    public static URL buildBackdropURL(String backdropPath) {
        Uri builtUri = Uri.parse(MOVIE_POSTER_BASE_URL).buildUpon()
                .appendPath(MOVIE_POSTER_SIZE)
                .appendPath(backdropPath).build();
        URL builtURL = null;

        try {
            builtURL = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return builtURL;
    }

    public static URL buildURL(Context activityContext) {
        return buildURL(MOVIE_POPULAR, activityContext);
    }

    //Method to return URL

    public static URL buildURL(String userChoice, Context activityContext) {
        //String apiKey = activityContext.getString(R.string.api_key);
        String apiKey = BuildConfig.API_KEY;
        Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon().appendPath(userChoice).appendQueryParameter(API_PARAM_NAME, apiKey).build();
        URL builtURL = null;

        try {
            builtURL = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return builtURL;
    }

    //Method to return full result from HTTP response

    public static String getResponseFromHttpURL(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput)
                return scanner.next();
            else
                return null;
        } finally {
            urlConnection.disconnect();
        }
    }
}
