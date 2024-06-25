package com.vishal.cinemate.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class NowPlaying implements Parcelable {

    private int page;
    private List<Results> results;
    private int total_pages;
    private int total_results;

    protected NowPlaying(Parcel in) {
        page = in.readInt();
        total_pages = in.readInt();
        total_results = in.readInt();
    }

    public static final Creator<NowPlaying> CREATOR = new Creator<NowPlaying>() {
        @Override
        public NowPlaying createFromParcel(Parcel in) {
            return new NowPlaying(in);
        }

        @Override
        public NowPlaying[] newArray(int size) {
            return new NowPlaying[size];
        }
    };

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }



    public int getTotal_pages() {
        return total_pages;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(page);
        parcel.writeInt(total_pages);
        parcel.writeInt(total_results);
    }


    public static class Results {
        private String poster_path;

        private int id;

        private String title;


        public String getPoster_path() {
            return poster_path;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        }
}
