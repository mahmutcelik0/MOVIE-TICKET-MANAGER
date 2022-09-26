package com.example.MODEL;

import java.util.ArrayList;
import java.util.List;

public class MOVIE {
    private String movieName;
    private int movieLength;
    private List<String> avaibleLanguages;
    private List<String> avaibleTypes;
    private String moviePremiereDate;
    private String movieLastDate;

    public MOVIE(String movieName, int movieLength, ArrayList<String> avaibleLanguages, ArrayList<String> avaibleTypes, String moviePremiereDate, String movieLastDate) {
        this.movieName = movieName;
        this.movieLength = movieLength;
        this.avaibleLanguages = avaibleLanguages;
        this.avaibleTypes = avaibleTypes;
        this.moviePremiereDate = moviePremiereDate;
        this.movieLastDate = movieLastDate;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getMovieLength() {
        return movieLength;
    }

    public List<String> getAvaibleLanguages() {
        return avaibleLanguages;
    }

    public List<String> getAvaibleTypes() {
        return avaibleTypes;
    }

    public String getMoviePremiereDate() {
        return moviePremiereDate;
    }

    public String getMovieLastDate() {
        return movieLastDate;
    }
}
