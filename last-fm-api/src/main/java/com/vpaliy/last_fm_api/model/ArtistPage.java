package com.vpaliy.last_fm_api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArtistPage {
    @SerializedName("artist")
    public List<Artist> artists;

    @SerializedName("@attr")
    public PageInfo info;
}
