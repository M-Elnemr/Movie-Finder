package com.example.getmovies.dataSource.movies;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.getmovies.model.responses.Result;

public class PopularDataSourceFactory extends DataSource.Factory<Integer, Result> {

    @NonNull
    @Override
    public DataSource<Integer, Result> create() {
        return new MoviesDataSource();
    }

}
