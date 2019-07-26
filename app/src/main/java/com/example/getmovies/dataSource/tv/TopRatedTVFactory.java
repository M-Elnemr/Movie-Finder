package com.example.getmovies.dataSource.tv;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;
import com.example.getmovies.model.responses.tvResponse.Result;

public class TopRatedTVFactory extends DataSource.Factory<Integer, Result> {
    @NonNull
    @Override
    public DataSource<Integer, Result> create() {
        return new TopRatedTVDataSource();
    }
}
