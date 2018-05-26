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