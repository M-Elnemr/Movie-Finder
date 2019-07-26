package com.example.getmovies.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.getmovies.model.responses.Result;
import com.example.getmovies.repositories.AllMoviesRepositories;

public class PopularMoviesViewModel extends AndroidViewModel {

    private AllMoviesRepositories popularMoviesRepositories;
    private LiveData<PagedList<Result>> moviesResponseLiveData;

    public PopularMoviesViewModel(@NonNull Application application) {
        super(application);
        popularMoviesRepositories = new AllMoviesRepositories();
        moviesResponseLiveData = popularMoviesRepositories.PopularMovies();
    }

    public LiveData<PagedList<Result>> getPublicMovies(){
        return moviesResponseLiveData;
    }
}
