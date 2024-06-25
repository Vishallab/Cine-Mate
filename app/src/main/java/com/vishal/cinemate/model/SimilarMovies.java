package com.vishal.cinemate.model;

import com.vishal.cinemate.R;

import java.util.List;

public class SimilarMovies {

    private int page;


    private List<Movie> results;
    private int total_pages;
    private int total_results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public int getTotalPages() {
        return total_pages;
    }

    public void setTotalPages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotalResults() {
        return total_results;
    }

    public void setTotalResults(int total_results) {
        this.total_results = total_results;
    }

    public static class Movie {
        private String title;
        private String overview;
        private String release_date;
        private String poster_path;
        private String backdrop_path;
        private double popularity;

        private double vote_average;
        private int vote_count;

        private String original_title;

        private String tagline;


        private int id;

        // Getter and Setter methods
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getOverview() {
            return overview;
        }

        public double getVote_average() {
            return vote_average;
        }


        public int getVote_count() {
            return vote_count;
        }


        public void setOverview(String overview) {
            this.overview = overview;
        }
        public String getOriginal_title(){
            return original_title;
        }

        public String getTagline() {
            return tagline;
        }

        public String getReleaseDate() {
            return release_date;
        }

        public void setReleaseDate(String release_date) {
            this.release_date = release_date;
        }

        public String getPosterPath() {
            return poster_path;
        }

        public void setPosterPath(String poster_path) {
            this.poster_path = poster_path;
        }

        public String getBackdropPath() {
            return backdrop_path;
        }

        public void setBackdropPath(String backdrop_path) {
            this.backdrop_path = backdrop_path;
        }

        public double getPopularity() {
            return popularity;
        }

        public void setPopularity(double popularity) {
            this.popularity = popularity;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }



    }


}
