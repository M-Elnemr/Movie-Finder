package com.example.getmovies.dataSource.tv;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.getmovies.BuildConfig;
import com.example.getmovies.model.responses.MoviesResponse;
import com.example.getmovies.model.responses.tvResponse.Result;
import com.example.getmovies.model.responses.tvResponse.TVResponse;
import com.example.getmovies.remote.APIInterface;
import com.example.getmovies.remote.RetrofitClient;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopRatedTVDataSource extends PageKeyedDataSource<Integer, Result> {

    private APIInterface apiInterface;

    public TopRatedTVDataSource(){
        apiInterface = RetrofitClient.getApi();
    }

    @Override
    public void loadInitial(@NonNull PageKeyedDataSource.LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Result> callback) {

        apiInterface.topRatedTV(BuildConfig.My_Token, 1).enqueue(new Callback<TVResponse>() {
            @Override
            public void onResponse(Call<TVResponse> call, Response<TVResponse> response) {
                if (response.isSuccessful()){
                    List<Result> tv;
                    if (response.body() != null){
                        tv = response.body().getResults();
                    }else {
                        tv = Collections.emptyList();
                    }
                    callback.onResult(tv, null, 2);
                }
            }

            @Override
            public void onFailure(Call<TVResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Result> callback) {
        apiInterface.topRatedTV(BuildConfig.My_Token, params.key).enqueue(new Callback<TVResponse>() {
            @Override
            public void onResponse(Call<TVResponse> call, Response<TVResponse> response) {
                if (response.isSuccessful()){
                    List<Result> tv;
                    if (response.body() != null){
                        tv = response.body().getResults();
                    }else {
                        tv = Collections.emptyList();
                    }
                    callback.onResult(tv, params.key - 1);
                }
            }

            @Override
            public void onFailure(Call<TVResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Result> callback) {
        apiInterface.topRatedTV(BuildConfig.My_Token, params.key).enqueue(new Callback<TVResponse>() {
            @Override
            public void onResponse(Call<TVResponse> call, Response<TVResponse> response) {
                if (response.isSuccessful()){
                    List<Result> tv;
                    if (response.body() != null){
                        tv = response.body().getResults();
                    }else {
                        tv = Collections.emptyList();
                    }
                    callback.onResult(tv, params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<TVResponse> call, Throwable t) {

            }
        });
    }
}
