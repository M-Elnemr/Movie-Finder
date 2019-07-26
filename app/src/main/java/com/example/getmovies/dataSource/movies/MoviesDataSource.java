package com.example.getmovies.dataSource.movies;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import com.example.getmovies.BuildConfig;
import com.example.getmovies.model.responses.AllMoviesResponse;
import com.example.getmovies.model.responses.Result;
import com.example.getmovies.remote.APIInterface;
import com.example.getmovies.remote.RetrofitClient;
import java.util.Collections;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesDataSource extends PageKeyedDataSource<Integer, Result> {

    private APIInterface apiInterface;

    public MoviesDataSource() {
        apiInterface = RetrofitClient.getApi();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Result> callback) {
        apiInterface.allMovies(BuildConfig.My_Token, 1).enqueue(new Callback<AllMoviesResponse>() {
            @Override
            public void onResponse(Call<AllMoviesResponse> call, Response<AllMoviesResponse> response) {
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
            public void onFailure(Call<AllMoviesResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Result> callback) {
        apiInterface.allMovies(BuildConfig.My_Token, params.key).enqueue(new Callback<AllMoviesResponse>() {
            @Override
            public void onResponse(Call<AllMoviesResponse> call, Response<AllMoviesResponse> response) {
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
            public void onFailure(Call<AllMoviesResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Result> callback) {
        apiInterface.allMovies(BuildConfig.My_Token, params.key).enqueue(new Callback<AllMoviesResponse>() {
            @Override
            public void onResponse(Call<AllMoviesResponse> call, Response<AllMoviesResponse> response) {
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
            public void onFailure(Call<AllMoviesResponse> call, Throwable t) {
            }
        });
    }
}
