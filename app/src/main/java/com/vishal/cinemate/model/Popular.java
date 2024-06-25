package com.vishal.cinemate.model;
import java.util.List;

public class Popular {

    private int total_pages;

    private int page;
    private List<Results> results;


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
