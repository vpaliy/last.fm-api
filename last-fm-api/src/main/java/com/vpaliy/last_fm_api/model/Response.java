package com.vpaliy.last_fm_api.model;

import com.google.gson.annotations.SerializedName;

public class Response<T> {

    @SerializedName(value = "result", alternate = {"weeklyalbumchart","track","artists"})
    public T result;

    public String message;
    public int error;
}
