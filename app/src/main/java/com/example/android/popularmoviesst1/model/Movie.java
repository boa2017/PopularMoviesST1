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

package com.example.android.popularmoviesst1.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.android.popularmoviesst1.utilities.NetworkUtils;

/**
 * Created by beita on 10/03/2018.
 */

public final class Movie implements Parcelable {

    public static final Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
    private int mRecuentoVotos;
    private int mID;
    private double mMediaVotos;
    private String mTitulo;
    private double mPopularidad;
    private String mPosterPath;
    private String mOverview;
    private String mFechaLanzamiento;
    private String mPosterURLString;
    private String mBackdropURLString;
    private String mBackdropPath;
    private String mChangeUponView;

    public Movie(int mVoteCount, int mID, double mMediaVotos, String mTitulo, double mPopularidad, String mPosterPath, String mBackdropPath, String mOverview, String mFechaLanzamiento) {
        this.mRecuentoVotos = mVoteCount;
        this.mID = mID;
        this.mMediaVotos = mMediaVotos;
        this.mTitulo = mTitulo;
        this.mPopularidad = mPopularidad;
        this.mPosterPath = mPosterPath;
        this.mBackdropPath = mBackdropPath;
        this.mOverview = mOverview;
        this.mFechaLanzamiento = mFechaLanzamiento;
        mPosterURLString = NetworkUtils.buildPosterURL(mPosterPath).toString();
        mBackdropURLString = NetworkUtils.buildBackdropURL(mBackdropPath).toString();
    }

    public Movie(Parcel in) {
        mRecuentoVotos = in.readInt();
        mID = in.readInt();
        mMediaVotos = in.readDouble();
        mTitulo = in.readString();
        mPopularidad = in.readDouble();
        mOverview = in.readString();
        mFechaLanzamiento = in.readString();
        mPosterURLString = in.readString();
        mBackdropURLString = in.readString();
    }

    public int getRecuentoVotos() {
        return mRecuentoVotos;
    }

    public int getID() {
        return mID;
    }

    public double getMediaVotos() {
        return mMediaVotos;
    }

    public String getTitulo() {
        return mTitulo;
    }

    public double getPopularidad() {
        return mPopularidad;
    }

    public String getOverview() {
        return mOverview;
    }

    public String getFechaLanzamiento() {
        return mFechaLanzamiento;
    }

    public String getPosterURLString() {
        return mPosterURLString;
    }

    public String getBackdropURLString() {
        return mBackdropURLString;
    }

    public String getChangeUponView() {
        return mChangeUponView;
    }

    public void setChangeUponView() {
        if (this.mChangeUponView == null)
            this.mChangeUponView = getChangeUponView() + " :)";
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mRecuentoVotos);
        dest.writeInt(mID);
        dest.writeDouble(mMediaVotos);
        dest.writeString(mTitulo);
        dest.writeDouble(mPopularidad);
        dest.writeString(mOverview);
        dest.writeString(mFechaLanzamiento);
        dest.writeString(mPosterURLString);
        dest.writeString(mBackdropURLString);

    }
}