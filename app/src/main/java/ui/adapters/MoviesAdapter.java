package ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movieapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import models.Movie;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    public ArrayList<Movie> movies ;
    public Context context;
    public String page = "1";

    public MoviesAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.movie_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Movie movie = movies.get(position);
        holder.title.setText(movie.getOriginalTitle());
        holder.overview.setText(movie.getOverview());
        holder.releasDate.setText(movie.getReleaseDate());
        //holder.imageView.setImageResource(listdata[position].getImgId());
        String url = "http://image.tmdb.org/t/p/w92//" + movie.getPosterPath();

        Picasso.with(context).load(url).into(holder.imageView);

    }



    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView title;
        public TextView overview;
        public TextView releasDate;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.image_movie_detail_poster);
            this.title = (TextView) itemView.findViewById(R.id.text_movie_original_title);
            this.overview = (TextView) itemView.findViewById(R.id.text_movie_overview);
            this.releasDate = (TextView) itemView.findViewById(R.id.text_movie_release_date);

        }
    }
}


