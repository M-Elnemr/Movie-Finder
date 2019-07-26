package com.example.getmovies.viewModel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.getmovies.model.responses.MovieVideosResponse;
import com.example.getmovies.repositories.MovieVideoRepository;

import java.util.List;

public class MovieVideosViewModel extends AndroidViewModel {

    private MovieVideoRepository repository;
    private LiveData<List<MovieVideosResponse.Result>> videos;

    public MovieVideosViewModel(@NonNull Application application) {
        super(application);

        repository = new MovieVideoRepository();
        videos = repository.getVideo();
    }

    public void vid(long id){
        repository.Video(id);
    }

    public void vidTV(long id){
        repository.VideoTV(id);
    }

    public LiveData<List<MovieVideosResponse.Result>> getVideos(){
        return videos;
    }
}
