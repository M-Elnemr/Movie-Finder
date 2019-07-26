package com.example.getmovies.remote;

import com.example.getmovies.model.responses.AllMoviesResponse;
import com.example.getmovies.model.responses.MoveDetailsResponse;
import com.example.getmovies.model.responses.MovieVideosResponse;
import com.example.getmovies.model.responses.MoviesResponse;
import com.example.getmovies.model.responses.tvResponse.TVResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("movie/{movie_id}")
    Call<MoveDetailsResponse> singleMovieDetails(@Path("movie_id") int id,
                                                 @Query("api_key") String apiKey );

    @GET("movie/now_playing")
    Call<AllMoviesResponse> allMovies (@Query("api_key") String apiKey,
                                       @Query("page") int page);

    @GET("movie/popular")
    Call<MoviesResponse> popularMovies (@Query("api_key") String apiKey,
                                        @Query("page") int page);

    @GET("movie/top_rated")
    Call<MoviesResponse> topRatedMovies (@Query("api_key") String apiKey,
                                         @Query("page") int page);

    @GET("movie/{movie_id}/videos")
    Call<MovieVideosResponse> getVideo (@Path("movie_id") long id,
                                        @Query("api_key") String apiKey);

    @GET("tv/now_playing")
    Call<TVResponse> allTV (@Query("api_key") String apiKey,
                            @Query("page") int page);

    @GET("tv/popular")
    Call<TVResponse> popularTV (@Query("api_key") String apiKey,
                                        @Query("page") int page);

    @GET("tv/top_rated")
    Call<TVResponse> topRatedTV (@Query("api_key") String apiKey,
                                         @Query("page") int page);

    @GET("tv/{tv_id}/videos")
    Call<MovieVideosResponse> getTVVideo (@Path("tv_id") long id,
                                        @Query("api_key") String apiKey);


//    search with title
//    https://api.themoviedb.org/3/search/movie?api_key=###&query=the+avengers

//    post a rate to a movie using POST
//    https://api.themoviedb.org/3/movie/{movie_id}/rating?api_key=<<api_key>>

//    delete rating using DELETE
//    https://api.themoviedb.org/3/movie/{movie_id}/rating?api_key=<<api_key>>


//    countries name
//    https://api.themoviedb.org/3/configuration/countries?api_key=359fe8f493e6dc697282cd9cb5a211c9


}
