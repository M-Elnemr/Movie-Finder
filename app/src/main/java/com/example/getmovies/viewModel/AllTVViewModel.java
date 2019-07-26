package com.example.getmovies.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.getmovies.model.responses.tvResponse.Result;
import com.example.getmovies.repositories.TVRepository;

public class AllTVViewModel extends AndroidViewModel {

    private LiveData<PagedList<Result>> allTV;


    public AllTVViewModel(@NonNull Application application) {
        super(application);
        allTV = new TVRepository().getAllTV();
    }

    public LiveData<PagedList<Result>> getAllTV(){
        return allTV;
    }
}
