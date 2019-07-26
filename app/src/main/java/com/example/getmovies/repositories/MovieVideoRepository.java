package com.example.getmovies.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.getmovies.BuildConfig;
import com.example.getmovies.model.responses.MovieVideosResponse;
import com.example.getmovies.remote.APIInterface;
import com.example.getmovies.remote.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieVideoRepository {

    private APIInterface apiInterface;
    private MutableLiveData<List<MovieVideosResponse.Result>> movieVideos = new MutableLiveData<>();

    public MovieVideoRepository() {
        apiInterface = RetrofitClient.getApi();
    }

    public void Video(long id){
        Call<MovieVideosResponse> call = apiInterface.getVideo(id, BuildConfig.My_Token);
        call.enqueue(new Callback<MovieVideosResponse>() {
            @Override
            public void onResponse(Call<MovieVideosResponse> call, Response<MovieVideosResponse> response) {
                if(response.isSuccessful()){
                    if (response.body() != null){
                        movieVideos.postValue(response.body().getResults());
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieVideosResponse> call, Throwable t) {

            }
        });
    }

    public void VideoTV(long id){
        Call<MovieVideosResponse> call = apiInterface.getTVVideo(id, BuildConfig.My_Token);
        call.enqueue(new Callback<MovieVideosResponse>() {
            @Override
            public void onResponse(Call<MovieVideosResponse> call, Response<MovieVideosResponse> response) {
                if(response.isSuccessful()){
                    if (response.body() != null){
                        movieVideos.postValue(response.body().getResults());
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieVideosResponse> call, Throwable t) {

            }
        });

    }

    public LiveData<List<MovieVideosResponse.Result>> getVideo(){
    return movieVideos;
    }
}
