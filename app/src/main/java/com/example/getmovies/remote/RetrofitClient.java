package com.example.getmovies.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static APIInterface apiInterface = null;
    private static Retrofit retrofit = null;
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String BASE_URL4 = "https://api.themoviedb.org/4/";


    public static APIInterface getApi(){
        if(apiInterface == null) {
            if (retrofit == null){

                 retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

            apiInterface = retrofit.create(APIInterface.class);
          }
        }

        return apiInterface;
    }

}
