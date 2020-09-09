package com.testapp.films.models;

import java.util.List;

public class Movies {
    private String imageMovies;
    private String nameMovies;
    private String yearMovies;
    private String ratingMovies;
    private String descriptionMovies;
    private String engNameMovies;

    public Movies(String imageMovies, String nameMovies, String yearMovies, String ratingMovies, String descriptionMovies, String engNameMovies, List<String> fitler) {
        this.imageMovies = imageMovies;
        this.nameMovies = nameMovies;
        this.yearMovies = yearMovies;
        this.ratingMovies = ratingMovies;
        this.descriptionMovies = descriptionMovies;
        this.engNameMovies = engNameMovies;
        this.fitler = fitler;
    }

    public String getYearMovies() {
        return yearMovies;
    }

    public String getRatingMovies() {
        return ratingMovies;
    }

    public String getDescriptionMovies() {
        return descriptionMovies;
    }

    public String getEngNameMovies() {
        return engNameMovies;
    }

    private List<String> fitler;


    public List<String> getFitler() {
        return fitler;
    }

    public String getImageMovies() {
        return imageMovies;
    }

    public String getNameMovies() {
        return nameMovies;
    }
}
