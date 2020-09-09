package com.testapp.films.client;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientRetrofit {
    private static final String ROOT_URL = "https://s3-eu-west-1.amazonaws.com";

    private static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static ApiService getApiService(){
        return getRetrofitInstance().create(ApiService.class);
    }
}
