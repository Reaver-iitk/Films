package com.testapp.films.client;

import com.testapp.films.retromodels.FilmList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/sequeniatesttask/films.json")
    Call<FilmList> getMyJSON();
}
