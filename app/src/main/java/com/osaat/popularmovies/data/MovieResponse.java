package com.osaat.popularmovies.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by osaat on 31/03/2017.
 */

public class MovieResponse {

        @SerializedName("page")
        private int page;
        @SerializedName("results")
        private List<Movie> results;
        @SerializedName("total_results")
        private int totalResults;
        @SerializedName("total_pages")
        private int totalPages;

        public int getPage() {
            return page;
        }

        public List<Movie> getResults() {
            return results;
        }

        public int getTotalResults() {
            return totalResults;
        }

        public int getTotalPages() {
            return totalPages;
        }
}
