package ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.movieapp.R;

import java.util.ArrayList;

import database.DatabaseManger;
import models.Movie;
import ui.adapters.MoviesAdapter;

public class MovieFragment extends Fragment implements MoviesViewer {

    private ArrayList<Movie> discoverMovies = new ArrayList<>();
    private ArrayList<Movie> searchMovies = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter adapter;
    private MoviesViewer viewer;
    MoviesPresenter presenter ;
    public Boolean isSearch = false;
    public String searchKey = "";
    public String discoverPage = "1";
    public String searchPage = "1";


    public MovieFragment() {
        // Required empty public constructor
    }

    public void updateMovies(ArrayList<Movie> movies){


        // Save Success Serach key to database
        if (isSearch){
            this.searchMovies.addAll(movies);
            adapter.movies .addAll(movies);
            DatabaseManger db = new DatabaseManger(getActivity().getApplicationContext());
            db.insertLabel(searchKey);
        }
        else {
            this.discoverMovies .addAll(movies);
            adapter.movies .addAll(movies);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        adapter = new MoviesAdapter(discoverMovies);
        adapter.context = getContext();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(adapter);

        // ADD ON SCROLL LISTENER TO DETECT THE THE MOVIES FINISHED


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == adapter.movies.size() - 1){
                    Integer pageNum = Integer.valueOf(adapter.page)+1 ;
                    adapter.page = String.valueOf(pageNum);
                    loadMovies();
                }
            }
        });

        viewer = this;
        presenter = new MoviesPresenter(viewer);
        loadMovies();



        return view;
    }

    private void loadMovies(){

        if (searchKey.equalsIgnoreCase("")){
            presenter.getData("",adapter.page);
        }

        else {
            presenter.getData(searchKey,adapter.page);
        }

    }
}
