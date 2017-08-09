package com.vpaliy.last_fm_api.model;

import com.google.gson.annotations.SerializedName;

public class Chart {
    @SerializedName("#text")
    public String text;

    public long from;
    public long to;
}
