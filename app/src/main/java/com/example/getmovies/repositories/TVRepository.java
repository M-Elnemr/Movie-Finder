package com.example.getmovies.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.getmovies.dataSource.tv.AllTVFactory;
import com.example.getmovies.dataSource.tv.PopularTVFactory;
import com.example.getmovies.dataSource.tv.TopRatedTVFactory;
import com.example.getmovies.model.responses.tvResponse.Result;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TVRepository {

    private Executor executor;

    public TVRepository(){
        executor = Executors.newFixedThreadPool(5);
    }

    public LiveData<PagedList<Result>> getAllTV(){
        AllTVFactory factory = new AllTVFactory();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(6)
                .setMaxSize(30)
                .setPageSize(10)
                .setPrefetchDistance(10)
                .build();

        return new LivePagedListBuilder<>(factory, config).setFetchExecutor(executor).build();
    }
    public LiveData<PagedList<Result>> getPopularTV(){

        PopularTVFactory factory = new PopularTVFactory();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(6)
                .setMaxSize(30)
                .setPageSize(10)
                .setPrefetchDistance(10)
                .build();

        return new LivePagedListBuilder<>(factory, config).setFetchExecutor(executor).build();
    }
    public LiveData<PagedList<Result>> getTopRatedTV(){
        TopRatedTVFactory factory = new TopRatedTVFactory();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(6)
                .setMaxSize(30)
                .setPageSize(10)
                .setPrefetchDistance(10)
                .build();

        return new LivePagedListBuilder<>(factory, config).setFetchExecutor(executor).build();
    }
}
