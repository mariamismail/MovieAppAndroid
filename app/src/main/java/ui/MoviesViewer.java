package ui;

import java.util.ArrayList;

import models.Movie;


public interface MoviesViewer {

    void updateMovies(ArrayList<Movie> movies);

    void showError(String error);
}
