package com.vpaliy.last_fm_api.model;

import com.google.gson.annotations.SerializedName;

public class Response<T> {

    @SerializedName(value = "album", alternate = {"weeklyalbumchart","track",
            "artists","artist","tags","toptags","topalbums","session"})
    public T result;

    public String message;
    public int error;
}
