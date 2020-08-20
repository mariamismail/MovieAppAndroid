package api;


import models.Movies;
import ui.MoviesPresenter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// class to be retofit client and controller

public class ApiClient implements Callback<Movies> {

    private MoviesPresenter presenter;
    static final String BASE_URL = "https://api.themoviedb.org/4/";
    static final String Header = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzNTZjZDg2NzgyOWY1ZDU2ZmJlNjY2Mjc4Y2EzODZiNyIsInN1YiI6IjU4MDlkYWExOTI1MTQxNzc3ZjAwMGZkNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.erwCx0Xfhr2zL492r137hC2uhizqRFrnpGriQWpLnV8";

    public ApiClient(MoviesPresenter presenter){
        this.presenter = presenter;
    }
    public void getMovies(String searchKeys, String page) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MoviesApi moviesApi = retrofit.create(MoviesApi.class);

        if (searchKeys.equalsIgnoreCase("")){

            Call<Movies> call = moviesApi.loadDiscoverMovies(page,Header);
            call.enqueue(this);
        }

       else {
            Call<Movies> call = moviesApi.loadSearchMovies(page,searchKeys,Header);
            call.enqueue(this);
        }


    }

    @Override
    public void onResponse(Call<Movies> call, Response<Movies> response) {
        if(response.isSuccessful()) {
            Movies moviesList = response.body();
            System.out.println(moviesList.getall().size());
            presenter.handleData(moviesList.getall());

        } else {
            System.out.println(response.errorBody());
            presenter.handleError(response.message());
        }
    }

    @Override
    public void onFailure(Call<Movies> call, Throwable t) {
      presenter.handleError(t.getLocalizedMessage());

        t.printStackTrace();
    }

}
