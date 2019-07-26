package com.example.getmovies.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import com.example.getmovies.model.responses.tvResponse.Result;
import com.example.getmovies.repositories.TVRepository;

public class PopularTVViewModel extends AndroidViewModel {
    private LiveData<PagedList<Result>> popularTV;
    private TVRepository tvRepository;


    public PopularTVViewModel(@NonNull Application application) {
        super(application);
        tvRepository = new TVRepository();
        popularTV = tvRepository.getPopularTV();
    }

    public LiveData<PagedList<Result>> getPopularTV(){
        return popularTV;
    }
}
