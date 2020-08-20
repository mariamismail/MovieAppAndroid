package ui;

import java.util.ArrayList;

import api.ApiClient;
import models.Movie;

// presenter class to movies fragment
public class MoviesPresenter {

    private MoviesViewer viwer;

    public MoviesPresenter(MoviesViewer viwer) {
       this.viwer = viwer;
    }

    protected   void getData( String searchKeys, String page){
        ApiClient apiclient = new ApiClient(this);
        apiclient.getMovies(searchKeys,page);
    }

    public void handleData(ArrayList<Movie> movies){

        viwer.updateMovies(movies);

    }

    public void handleError(String error){
        viwer.showError(error);
    }
}
