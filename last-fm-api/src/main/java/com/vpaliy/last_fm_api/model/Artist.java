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
    public long playcount;
    public int tagcount;

    @SerializedName("stats")
    private Stats stats;

    private static class Stats{
        public long listeners;
        public long playcount;
    }

    @Override
    public void postProcess() {
        if(stats!=null){
            listeners=stats.listeners;
            playcount=stats.playcount;
            stats=null;
        }
        if(result(tags)!=null){
            tagcount=result(tags).size();
        }
    }

    @SuppressWarnings("WeakerAccess")
    public class Wrapper<T> {
        @SerializedName(value = "artist",alternate = "tag")
        public List<T> result;
    }

    public List<Tag> tags(){
        return result(tags);
    }

    public List<Artist> similar(){
        return result(similar);
    }

    private <T> List<T> result(Wrapper<T> wrapper){
        if(wrapper==null) return null;
        return wrapper.result;
    }
}
