package com.vpaliy.last_fm_api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    public String id;
    public String name;
    public String realname;
    public String url;
    public List<Image> image;
    public String country;
    public int age;
    public String gender;
    public long subscriber;
    public long playcount;
    public long playlists;
    public int bootstrap;
    public String type;
    public Registered registered;

    public class Registered{
        @SerializedName("text")
        public String text;
        public String unixtime;
    }
}
