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
