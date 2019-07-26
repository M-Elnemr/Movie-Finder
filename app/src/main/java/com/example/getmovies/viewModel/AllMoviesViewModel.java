package com.example.getmovies.viewModel;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.getmovies.model.responses.Result;
import com.example.getmovies.repositories.AllMoviesRepositories;

public class AllMoviesViewModel extends AndroidViewModel {

    private LiveData<PagedList<Result>> moviesLiveList;
    private AllMoviesRepositories allMoviesRepositories;

    public AllMoviesViewModel(@NonNull Application application) {
        super(application);
        allMoviesRepositories = new AllMoviesRepositories();

        moviesLiveList = allMoviesRepositories.getAllMovies();
    }

    public LiveData<PagedList<Result>> getMoviesLiveList(){
        return moviesLiveList;
    }
}
