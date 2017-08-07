package com.vpaliy.last_fm_api.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Track {
    public String id;
    public String name;
    public String mbid;
    public String url;
    public String duration;
    public long listeners;
    public long playcount;
    public Artist artist;
    public Album album;
    public Wrapper<Tag> toptags;
    public Wiki wiki;
    public Streamable streamable;
    public List<Image> image;

    @SuppressWarnings("WeakerAccess")
    public class Wrapper<T> {
        @SerializedName(value = "tag",alternate = "track")
        public List<T> result;
    }

    public class Streamable {
        @SerializedName("#text")
        private boolean text;

        @SerializedName("fulltrack")
        private boolean fulltrack;
    }
}
