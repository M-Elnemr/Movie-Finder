package com.example.getmovies.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.getmovies.model.MovieShortDetails;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM MovieShortDetails")
    LiveData<List<MovieShortDetails>> getFavMovies();

    @Query("SELECT * FROM MovieShortDetails")
    List<MovieShortDetails> getFavMoviesData();

    @Insert
    void addFavMovie(MovieShortDetails movieShortDetails);

    @Delete
    void deleteFavMovie(MovieShortDetails movieShortDetails);

    @Query("DELETE FROM MovieShortDetails WHERE mOriginalTitle = :title")
    void deleteFavMovieTitle(String title);

}
