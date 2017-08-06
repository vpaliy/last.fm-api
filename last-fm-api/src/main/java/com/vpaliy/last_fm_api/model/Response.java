package com.vpaliy.last_fm_api.model;

import com.google.gson.annotations.SerializedName;

public class Response<T> {

    @SerializedName(value = "album",alternate = {"weeklyalbumchart","artist"})
    public T album;

    public String message;
    public int error;
}
