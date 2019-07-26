package com.example.getmovies.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class MovieShortDetails {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "movieID")
    private long movieID;
    @ColumnInfo(name = "mOriginalTitle")
    private String mOriginalTitle;
    @ColumnInfo(name = "mOverview")
    private String mOverview;
    @ColumnInfo(name = "mPosterPath")
    private String mPosterPath;
    @ColumnInfo(name = "mReleaseDate")
    private String mReleaseDate;
    @ColumnInfo(name = "mVoteAverage")
    private Double mVoteAverage;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String type() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long movieID() {
        return movieID;
    }

    public void setMovieID(long movieID) {
        this.movieID = movieID;
    }

    public String mOriginalTitle() {
        return mOriginalTitle;
    }

    public void setmOriginalTitle(String mOriginalTitle) {
        this.mOriginalTitle = mOriginalTitle;
    }

    public String mOverview() {
        return mOverview;
    }

    public void setmOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    public void setmPosterPath(String mPosterPath) {
        this.mPosterPath = mPosterPath;
    }

    public String mReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public Double mVoteAverage() {
        return mVoteAverage;
    }

    public void setmVoteAverage(Double mVoteAverage) {
        this.mVoteAverage = mVoteAverage;
    }

    public String mPosterPath() {
        return mPosterPath;
    }

    public MovieShortDetails(Long movieID,String type, String mOriginalTitle, String mOverview, String mPosterPath, String mReleaseDate, Double mVoteAverage) {
        this.movieID = movieID;
        this.type = type;
        this.mOriginalTitle = mOriginalTitle;
        this.mOverview = mOverview;
        this.mPosterPath = mPosterPath;
        this.mReleaseDate = mReleaseDate;
        this.mVoteAverage = mVoteAverage;
    }
}
