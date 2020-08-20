package ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
//import android.support.v7.widget.SearchView;


import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.movieapp.R;

import java.util.ArrayList;

import database.DatabaseManger;

public class SearchFragment extends Fragment {
    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String > adapter;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchView = (SearchView)view.findViewById(R.id.searchView);

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
       // Replace the contents of the container with the new fragment
        ft.add(R.id.search_frame, new SearchResult());
        //ft.replace(R.id.search_frame, new SearchResult());

        ft.commit();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(!query.isEmpty()){
                    //adapter.getFilter().filter(query);
                    MovieFragment movief = new MovieFragment();
                    movief.searchKey = query;
                    movief.isSearch = true;
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    // Replace the contents of the container with the new fragment
                    ft.replace(R.id.search_frame, movief);

                    ft.commit();
                    DatabaseManger db = new DatabaseManger(getActivity().getApplicationContext());
                    db.insertLabel(query);
                }else{
                    Toast.makeText(getActivity(), "No Match found", Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //    adapter.getFilter().filter(newText);
                return false;
            }
        });
        return  view;
    }

}
