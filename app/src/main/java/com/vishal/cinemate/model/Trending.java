package com.vishal.cinemate.model;

import java.util.List;

public class Trending {

    private int page;
    private List<Results> results;
    private int total_results;

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



    public static class Results {
        private String backdrop_path;
        private int id;
        private String title;

        private String name;


        public String getBackdrop_path() {
            return backdrop_path;
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


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }
}
