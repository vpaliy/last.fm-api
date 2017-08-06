package com.vpaliy.last_fm_api.model;

import com.google.gson.annotations.SerializedName;

public class Response<T> {

    @SerializedName("topalbums")
    public T album;
}
