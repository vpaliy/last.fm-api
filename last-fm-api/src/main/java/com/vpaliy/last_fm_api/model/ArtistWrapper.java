package com.vpaliy.last_fm_api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArtistWrapper {

    @SerializedName("artists")
    public Wrapper wrapper;

    public class Wrapper {
        @SerializedName("artist")
        public List<Artist> artist;

        public int page;
        public int perPage;
        public int totalPages;
        public int total;
    }
}
