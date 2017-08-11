package com.vpaliy.lastfmwrapper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vpaliy.last_fm_api.auth.LastFmUpdate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LastFmUpdate.create(null)
                .addTagsToAlbum("album","artist","something","another");

    }
}

