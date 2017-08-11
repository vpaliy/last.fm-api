package com.vpaliy.lastfmwrapper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.vpaliy.last_fm_api.LastFm;
import com.vpaliy.last_fm_api.LastFmService;
import com.vpaliy.last_fm_api.ServiceProvider;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG=MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LastFm.create(Config.API_KEY)
                .createService(this)
                .fetchArtist("Imagine Dragons")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    Log.d(TAG,response.result.name);
                });
    }
}

