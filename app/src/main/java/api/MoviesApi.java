package api;

import models.Movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface MoviesApi {

    // load discover movies

    @GET("discover/movie")
    Call<Movies> loadDiscoverMovies(@Query("page") String page, @Header("Authorization") String credentials);


    //load search movies
    @GET("search/movie")
    Call<Movies> loadSearchMovies(@Query("page") String page,@Query("query") String query,@Header("Authorization") String credentials);

}
