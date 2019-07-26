package com.example.getmovies.dataSource.tv;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;
import com.example.getmovies.model.responses.tvResponse.Result;

public class PopularTVFactory extends DataSource.Factory<Integer, Result> {
    @NonNull
    @Override
    public DataSource<Integer, Result> create() {

        return new PopularTVDataSource();
    }
}
