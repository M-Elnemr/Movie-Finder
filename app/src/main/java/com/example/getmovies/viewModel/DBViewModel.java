package com.example.getmovies.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.getmovies.model.MovieShortDetails;
import com.example.getmovies.model.responses.MoveDetailsResponse;
import com.example.getmovies.repositories.DBRepository;

import java.util.List;

public class DBViewModel extends AndroidViewModel {
    private LiveData<List<MovieShortDetails>> movies;
    private List<MovieShortDetails> movieList;
    private DBRepository dbRepository;

    public DBViewModel(@NonNull Application application) {
        super(application);
        dbRepository = new DBRepository(application);
        movies = dbRepository.getFavMovies();
        movieList = dbRepository.getFavMoviesData();
    }

    public List<MovieShortDetails> getFavMovies(){
        return movieList;
    }

    public LiveData<List<MovieShortDetails>> getMovies(){
        return movies;
    }

    public void deleteMovie(MovieShortDetails movieShortDetails){
        dbRepository.deleteMovie(movieShortDetails);
    }

    public void deleteMovieTitle(String title){
        dbRepository.deleteMovieTitle(title);
    }

    public void addMovie(MovieShortDetails movieShortDetails){
        dbRepository.addMovie(movieShortDetails);
    }

}
