package com.vpaliy.last_fm_api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {
    @Expose
    @SerializedName("#text")
    public String text;

    @Expose
    public String size;
}
