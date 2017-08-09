package com.vpaliy.last_fm_api.model;

import com.google.gson.annotations.SerializedName;
import com.vpaliy.last_fm_api.Adapter;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class TagPage implements Adapter.PostProcessable {

    public List<Tag> tag;

    public String artist;
    public String album;
    public int page;
    public int perPage;
    public int totalPages;
    public int total;

    @SerializedName("@attr")
    private PageInfo attr;

    private class PageInfo {
        public String artist;
        public String album;
        public int page;
        public int perPage;
        public int totalPages;
        public int total;
    }

    @Override
    public void postProcess() {
        if(attr!=null){
            this.artist=attr.artist;
            this.album=attr.album;
            this.page=attr.page;
            this.perPage=attr.perPage;
            this.totalPages=attr.totalPages;
            this.total=attr.total;
        }
    }
}
