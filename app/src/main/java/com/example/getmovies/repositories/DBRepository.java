package com.example.getmovies.repositories;

import android.app.Application;
import android.graphics.Movie;

import androidx.lifecycle.LiveData;
import com.example.getmovies.db.AppDatabase;
import com.example.getmovies.db.MovieDao;
import com.example.getmovies.model.MovieShortDetails;
import com.example.getmovies.movieInterface.LoadData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DBRepository implements LoadData.MovieInterFace {
    private LiveData<List<MovieShortDetails>> movies;
    private List<MovieShortDetails> movieList;
    private Executor executor;
    private MovieDao dao;
    private LoadData loadData;

    public DBRepository(Application application) {
        loadData = new LoadData(application);
        AppDatabase database = AppDatabase.getINSTANCE(application);
        dao = database.movieDao();
        movies = dao.getFavMovies();
        executor = Executors.newFixedThreadPool(5);
    }

    public void deleteMovie(MovieShortDetails movieShortDetails){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dao.deleteFavMovie(movieShortDetails);
            }
        });
    }

    public void deleteMovieTitle(String title){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dao.deleteFavMovieTitle(title);
            }
        });
    }

    public void addMovie(MovieShortDetails movieShortDetails){
        executor.execute(new Runnable() {
            @Override
            public void run() {

                dao.addFavMovie(movieShortDetails);
            }
        });
    }

    public LiveData<List<MovieShortDetails>> getFavMovies(){
        return movies;
    }

    public List<MovieShortDetails> getFavMoviesData(){
        loadData.getFavMovies(this);

        return movieList;
    }


    @Override
    public void onSucceed(List<MovieShortDetails> movieShortDetails) {
        movieList = movieShortDetails;
    }
}
