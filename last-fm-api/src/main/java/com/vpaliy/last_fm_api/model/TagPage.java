package com.vpaliy.last_fm_api.model;


import com.google.gson.annotations.SerializedName;
import com.vpaliy.last_fm_api.Adapter;

import java.util.List;

@SuppressWarnings("WeakerAccess")
public class TagPage implements Adapter.PostProcessable {

    public List<Tag> tag;

    public String artist;
    public String album;

    @SerializedName("@attr")
    private PageInfo attr;

    private class PageInfo {
        public String artist;
        public String album;
    }

    @Override
    public void postProcess() {
        if(attr!=null){
            this.artist=attr.artist;
            this.album=attr.album;
        }
    }
}
