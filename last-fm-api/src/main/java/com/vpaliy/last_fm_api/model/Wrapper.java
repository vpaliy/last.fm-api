package com.vpaliy.last_fm_api.model;

import com.google.gson.annotations.SerializedName;

public class Wrapper<T> {

    @SerializedName(alternate = {"tags","artists"},
            value = "tracks")
    protected T wrapper;
}
