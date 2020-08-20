package models;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

import com.google.gson.annotations.SerializedName;


public class Movie implements Serializable {


//    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
//        @Override
//        public Movie createFromParcel(Parcel source) {
//            return new Movie(source);
//        }
//
//        @Override
//        public Movie[] newArray(int size) {
//            return new Movie[size];
//        }
//    };

    @SerializedName("id")
    private long id;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("poster_path")
    private String posterPath;


//    public Movie(Parcel in) {
//        this.id = in.readLong();
//        this.originalTitle = in.readString();
//        this.overview = in.readString();
//        this.releaseDate = in.readString();
//        this.posterPath = in.readString();
//
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeLong(this.id);
//        dest.writeString(this.originalTitle);
//        dest.writeString(this.overview);
//        dest.writeString(this.releaseDate);
//        dest.writeString(this.posterPath);
//
//    }
}
