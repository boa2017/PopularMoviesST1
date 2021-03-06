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

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmoviesst1.R;
import com.example.android.popularmoviesst1.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by beita on 10/03/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    //Member Variables
    private static final String LOG_TAG = MovieAdapter.class.getSimpleName();
    private final ListItemOnClickHandler mClickHandler;
    private Context mContext;
    private ArrayList<Movie> mMovieDataList;

    public MovieAdapter(Activity mainActivity, ListItemOnClickHandler handler, ArrayList<Movie> movieList) {
        mContext = mainActivity;
        mClickHandler = handler;
        mMovieDataList = movieList;
    }

    @Override
    public int getItemCount() {
        if (mMovieDataList == null || mMovieDataList.isEmpty())
            return 0;
        else
            return mMovieDataList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int LayoutIDForView = R.layout.movie_view;
        boolean shouldAttachToParentImmediately = false;
        LayoutInflater movieViewLayoutInflater = LayoutInflater.from(context);
        View movieView = movieViewLayoutInflater.inflate(LayoutIDForView, viewGroup, shouldAttachToParentImmediately);
        return new ViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Movie movieDataForThisView = mMovieDataList.get(position);
        String posterURIString = movieDataForThisView.getPosterURLString();
        viewHolder.mMovieTitle.setText(mMovieDataList.get(position).getTitulo());
        Picasso.with(mContext).load(posterURIString).placeholder(R.mipmap.moviesimg).error(R.mipmap.no_image).into(viewHolder.mMoviePoster);
    }

    //Interface Listener Handler
    public interface ListItemOnClickHandler {
        void onClick(Movie movieClicked);
    }

    //Class ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mMoviePoster;
        private TextView mMovieTitle;

        //Constructor
        public ViewHolder(View itemView) {
            super(itemView);
            mMoviePoster = (ImageView) itemView.findViewById(R.id.movie_poster);
            mMovieTitle = (TextView) itemView.findViewById(R.id.tv_movie_title);

            showMoviePoster();
            showMovieTitle();
            itemView.setOnClickListener(this);
        }

        public void showMoviePoster() {
            mMoviePoster.setVisibility(View.VISIBLE);
        }

        public void showMovieTitle() {
            mMovieTitle.setVisibility(View.VISIBLE);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Movie movieDataForThisView = mMovieDataList.get(adapterPosition);
            mClickHandler.onClick(movieDataForThisView);
        }
    }
}