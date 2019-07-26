package com.example.getmovies.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.getmovies.model.responses.tvResponse.Result;
import com.example.getmovies.repositories.TVRepository;

public class TopRatedTVViewModel extends AndroidViewModel {

    private LiveData<PagedList<Result>> topRatedTV;
    private TVRepository tvRepository;

    public TopRatedTVViewModel(@NonNull Application application) {
        super(application);
        tvRepository = new TVRepository();
        topRatedTV = tvRepository.getTopRatedTV();
    }

    public LiveData<PagedList<Result>> getTopRatedTV(){
        return topRatedTV;
    }
}
