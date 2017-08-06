package com.vpaliy.last_fm_api.model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.vpaliy.last_fm_api.Adapter;

import java.util.List;

@SuppressWarnings("WeakerAccess")
public class Artist implements Adapter.PostProcessable{

    @SerializedName(value = "name",alternate = "#text")
    public String name;
    public String mbid;
    public String url;
    public boolean streamable;
    public boolean outour;
    public List<Image> image;
    public Wrapper<Artist> similar;
    public Wrapper<Tag> tags;
    public Wiki bio;
    public long listeners;
    public long plays;
    public int playcount;

    @SerializedName("stats")
    private Stats stats;


    private static class Stats{
        public long listeners;
        public long plays;
    }

    @Override
    public void postProcess() {
        if(stats!=null){
            listeners=stats.listeners;
            plays=stats.plays;
            stats=null;
        }
    }

    @SuppressWarnings("WeakerAccess")
    public class Wrapper<T> {
        @SerializedName(value = "artist",alternate = "tag")
        public List<T> result;
    }
}
