package com.example.getmovies.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.getmovies.model.responses.Result;
import com.example.getmovies.repositories.AllMoviesRepositories;

public class TopRatedMoviesViewModel extends AndroidViewModel {

    private AllMoviesRepositories topRatedMoviesRepositories;
    private LiveData<PagedList<Result>> moviesResponseLiveData;

    public TopRatedMoviesViewModel(@NonNull Application application) {
        super(application);

        topRatedMoviesRepositories = new AllMoviesRepositories();
        moviesResponseLiveData = topRatedMoviesRepositories.getTopRatedMovies();
    }

    public LiveData<PagedList<Result>> getTopRatedMovies(){
        return moviesResponseLiveData;
    }
}
