package com.example.getmovies.movieInterface;

import android.app.Application;

import com.example.getmovies.db.AppDatabase;
import com.example.getmovies.db.MovieDao;
import com.example.getmovies.model.MovieShortDetails;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoadData {

    private Executor executor;
    private MovieDao dao;

    public LoadData(Application application) {
        executor = Executors.newFixedThreadPool(5);
        dao = AppDatabase.getINSTANCE(application).movieDao();
    }

    public interface MovieInterFace{
        void onSucceed(List<MovieShortDetails> movieShortDetails);
    }

    public void getFavMovies(MovieInterFace interFace){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                interFace.onSucceed(dao.getFavMoviesData());
            }
        });
    }

}
