package com.vpaliy.last_fm_api;

import android.content.Context;

@SuppressWarnings({"unused","WeakerAccess"})
public class LastFm extends ServiceProvider<LastFmService> {

    public LastFm(String key){
        super(key);
    }

    protected Class<LastFmService> clazz(){
        return LastFmService.class;
    }

    public LastFmService createService(Context context){
        return super.createService(context);
    }

    public static LastFm create(String key){
        return new LastFm(key);
    }
}
