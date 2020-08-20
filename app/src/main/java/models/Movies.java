package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Movies implements Serializable {

    @SerializedName("results")
    @Expose
    private ArrayList<Movie> all = null ;

    public ArrayList<Movie> getall() {
        return all;
    }

    public void setall(ArrayList<Movie> movieArray) {
        this.all = movieArray;
    }
}
