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

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.popularmoviesst1.model.Movie;
import com.example.android.popularmoviesst1.utilities.MovieAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by beita on 10/03/2018.
 */

public class MovieFragment extends Fragment implements MovieAdapter.ListItemOnClickHandler {

    //Member Variables
    private final static int GRID_COLUMN_COUNT = 2;
    private final static String PARCELABLE_EXTRA_KEY = "Movie";
    private final static String PARCELABLE_SAVE_STATE_KEY = "MovieArrayList";
    //Bind Views
    @BindView(R.id.movie_display)
    RecyclerView mMovieDisplay;
    @BindView(R.id.error_message_display)
    TextView mErrorMessage;
    private boolean mSavedInstanceState;
    private String mUserSort;
    private RecyclerView.LayoutManager layoutManager;
    private MovieAdapter mMovieAdapter;
    private ArrayList<Movie> mMovieDataList;
    private Context mMovieActivityContext;
    private Unbinder unbinder;

    public MovieFragment() {

    }

    public void setMovieAdapter() {
        mMovieAdapter = new MovieAdapter(getActivity(), MovieFragment.this, mMovieDataList);
        mMovieDisplay.setAdapter(mMovieAdapter);
    }

    public void setSortChoice(String sortChoice) {
        this.mUserSort = sortChoice;
    }

    public void setSavedInstanceState(boolean savedInstanceState) {
        this.mSavedInstanceState = savedInstanceState;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //Saved Instance State
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null || !savedInstanceState.containsKey(PARCELABLE_SAVE_STATE_KEY)) {
            setSavedInstanceState(false);
        } else {
            setSavedInstanceState(true);
            mMovieDataList = savedInstanceState.getParcelableArrayList(PARCELABLE_SAVE_STATE_KEY);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!(mMovieDataList == null || mMovieDataList.isEmpty()))
            outState.putParcelableArrayList(PARCELABLE_SAVE_STATE_KEY, mMovieDataList);
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        Boolean shouldAttachToParentImmediately = false;
        View movieFragmentView = layoutInflater.inflate(R.layout.movie_fragment, viewGroup, shouldAttachToParentImmediately);

        //View Binder
        unbinder = ButterKnife.bind(this, movieFragmentView);

        showMovieDataView();

        layoutManager = new GridLayoutManager(mMovieActivityContext, GRID_COLUMN_COUNT);
        mMovieDisplay.setLayoutManager(layoutManager);
        mMovieDisplay.setHasFixedSize(true);

        //Load Movies
        loadMovieData();

        return movieFragmentView;

    }

    //ButterKnife Unbind
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mMovieActivityContext = context;
    }

    public void loadMovieData() {
        showMovieDataView();
        new MovieFetcher(this).execute(mUserSort);
    }

    //Show Error Message
    public void showErrorMessageView() {
        mMovieDisplay.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.VISIBLE);
    }

    public void showMovieDataView() {
        mMovieDisplay.setVisibility(View.VISIBLE);
        mErrorMessage.setVisibility(View.INVISIBLE);
    }

    public void setMovieData(Movie[] movieData) {
        if (!mSavedInstanceState)
            mMovieDataList = new ArrayList<>(Arrays.asList(movieData));
        setSavedInstanceState(false);
    }

    @Override
    public void onClick(Movie movieClicked) {
        Class detailActivity = com.example.android.popularmoviesst1.MovieDetailActivity.class;
        Intent detailIntent = new Intent(mMovieActivityContext, detailActivity);
        detailIntent.putExtra(PARCELABLE_EXTRA_KEY, movieClicked);
        startActivity(detailIntent);
    }
}


