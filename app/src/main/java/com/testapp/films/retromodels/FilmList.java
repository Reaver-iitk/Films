package com.testapp.films.retromodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilmList {

    @SerializedName("films")
    @Expose
    private List<Films> films = null;

    public List<Films> getFilms() {
        return films;
    }

    public void setFilms(List<Films> films) {
        this.films = films;
    }
}