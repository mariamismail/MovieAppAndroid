package ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.movieapp.R;

import java.util.List;

import database.DatabaseManger;

public class SearchResult extends Fragment {

    ListView listView;
    List<String> list;
    ArrayAdapter<String > adapter;

    public SearchResult() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);

        listView = (ListView)view.findViewById(R.id.lv1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
                MovieFragment movief = new MovieFragment();
                movief.searchKey = list.get(position);
                movief.isSearch = true;

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                // Replace the contents of the container with the new fragment
                ft.replace(R.id.search_frame, movief);

                ft.commit();
            }
        });

        DatabaseManger db = new DatabaseManger(getActivity().getApplicationContext());
        list  = db.getAllLabels();
//        list = new ArrayList<>();
//        list.add("Apple");
//        list.add("Banana");
//        list.add("Pineapple");
//        list.add("Orange");
//        list.add("Lychee");
//        list.add("Gavava");
//        list.add("Peech");
//        list.add("Melon");
//        list.add("Watermelon");
//        list.add("Papaya");

        adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);


        return  view;
    }

}
