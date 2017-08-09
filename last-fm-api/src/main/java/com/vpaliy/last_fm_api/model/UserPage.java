package com.vpaliy.last_fm_api.model;

import com.google.gson.annotations.SerializedName;
import com.vpaliy.last_fm_api.Adapter;

import java.util.List;

public class UserPage implements Adapter.PostProcessable{

    public List<User> user;

    public int page;
    public int perPage;
    public int totalPages;
    public int total;
    public String forUser;

    @SerializedName("@attr")
    private PageInfo info;

    public class PageInfo{
        public int page;
        public int perPage;
        public int totalPages;
        public int total;
        @SerializedName("for")
        public String forUser;
    }

    @Override
    public void postProcess() {
        if(info!=null){
            this.page=info.page;
            this.perPage=info.perPage;
            this.totalPages=info.totalPages;
            this.total=info.total;
            this.forUser=info.forUser;
        }
    }
}
