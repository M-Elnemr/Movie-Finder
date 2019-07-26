package com.example.getmovies.dataSource.movies;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.getmovies.BuildConfig;
import com.example.getmovies.model.responses.MoviesResponse;
import com.example.getmovies.model.responses.Result;
import com.example.getmovies.remote.APIInterface;
import com.example.getmovies.remote.RetrofitClient;
import java.util.Collections;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularDataSource extends PageKeyedDataSource<Integer, Result> {

    private APIInterface apiInterface;

    public PopularDataSource() {
        apiInterface = RetrofitClient.getApi();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Result> callback) {
        apiInterface.popularMovies(BuildConfig.My_Token, 1).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if(response.isSuccessful()){
                    List<Result> movie ;
                    if (response.body() != null){
                        movie = response.body().getResults();
                    }else {
                        movie = Collections.emptyList();
                    }
                    callback.onResult(movie, null, 2);
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Result> callback) {
       apiInterface.popularMovies(BuildConfig.My_Token, params.key).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response.isSuccessful()){
                    List<Result> movies;
                    if (response.body() != null){
                        movies = response.body().getResults();
                    }else {
                        movies = Collections.emptyList();
                    }
                    callback.onResult(movies, params.key - 1);
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Result> callback) {
        apiInterface.popularMovies(BuildConfig.My_Token, params.key).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if(response.isSuccessful()){
                    List<Result> movies;
                    if (response.body() != null){
                        movies = response.body().getResults();
                    }else {
                        movies = Collections.emptyList();
                    }
                    callback.onResult(movies, params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
            }
        });
    }
}
