package com.vpaliy.last_fm_api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Artist {
    @SerializedName(value = "name",alternate = "#text")
    public String name;
    public String mbid;
    public String url;
    public boolean streamable;
    public boolean ontour;
    public List<Image> image;
    public Wrapper<Artist> similar;
    public Wrapper<Tag> tags;
    public Wiki bio;

    @SuppressWarnings("WeakerAccess")
    public class Wrapper<T> {
        @SerializedName(value = "similar",alternate = "tags")
        public List<T> result;
    }

}
