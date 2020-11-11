package com.example.assignment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResponseModel {

    @SerializedName("Search")
    @Expose
    public List<Search> search = null;
    @SerializedName("totalResults")
    @Expose
    public String totalResults;
    @SerializedName("Response")
    @Expose
    public String response;

    public List<Search> getSearch() {
        return search;
    }

    public void setSearch(List<Search> search) {
        this.search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public class Search {

        @SerializedName("Title")
        @Expose
        public String title;
        @SerializedName("Year")
        @Expose
        public String year;
        @SerializedName("imdbID")
        @Expose
        public String imdbID;
        @SerializedName("Type")
        @Expose
        public String type;
        @SerializedName("Poster")
        @Expose
        public String poster;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getImdbID() {
            return imdbID;
        }

        public void setImdbID(String imdbID) {
            this.imdbID = imdbID;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

    }
}
