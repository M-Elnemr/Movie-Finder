package com.example.getmovies.repositories;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.getmovies.dataSource.movies.MoviesDataSourceFactory;
import com.example.getmovies.dataSource.movies.PopularDataSourceFactory;
import com.example.getmovies.dataSource.movies.TopRatedDataSourceFactory;
import com.example.getmovies.model.responses.Result;
import com.example.getmovies.remote.APIInterface;
import com.example.getmovies.remote.RetrofitClient;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AllMoviesRepositories {

    private Executor executor;

    public AllMoviesRepositories(){
        executor = Executors.newFixedThreadPool(5);
    }

    public LiveData<PagedList<Result>> getAllMovies(){

        MoviesDataSourceFactory factory = new MoviesDataSourceFactory();

        PagedList.Config pagedConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(6)
                .setMaxSize(30)
                .setPageSize(10)
                .setPrefetchDistance(10)
                .build();

        return new LivePagedListBuilder<>(factory, pagedConfig).setFetchExecutor(executor).build();
    }

    public LiveData<PagedList<Result>> PopularMovies(){

        PopularDataSourceFactory factory = new PopularDataSourceFactory();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(6)
                .setMaxSize(30)
                .setPageSize(10)
                .setPrefetchDistance(10)
                .build();

        return new LivePagedListBuilder<>(factory, config).setFetchExecutor(executor).build();
    }

    public LiveData<PagedList<Result>> getTopRatedMovies(){

        TopRatedDataSourceFactory factory = new TopRatedDataSourceFactory();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setInitialLoadSizeHint(6)
                .setEnablePlaceholders(true)
                .setMaxSize(30)
                .setPageSize(10)
                .setPrefetchDistance(10)
                .build();

        return new LivePagedListBuilder<>(factory, config).setFetchExecutor(executor).build();
    }

}
