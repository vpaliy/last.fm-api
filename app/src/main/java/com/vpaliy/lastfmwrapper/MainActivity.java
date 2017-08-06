package com.vpaliy.lastfmwrapper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.vpaliy.last_fm_api.LastFmApi;
import com.vpaliy.last_fm_api.LastFmService;
import com.vpaliy.last_fm_api.model.Album;
import com.vpaliy.last_fm_api.model.AlbumWrapper;
import com.vpaliy.last_fm_api.model.Artist;
import com.vpaliy.last_fm_api.model.ArtistWrapper;
import com.vpaliy.last_fm_api.model.Image;
import com.vpaliy.last_fm_api.model.Response;
import com.vpaliy.last_fm_api.model.Tag;
import com.vpaliy.last_fm_api.model.Track;
import com.vpaliy.last_fm_api.model.Wiki;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG=MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LastFmService service=LastFmApi.create("fe109013cea138dbd787b4f17bff90cb")
                .createService(this);

        service.fetchArtist("Imagine Dragons")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<Artist>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG,"omCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Response<Artist> response) {
                        Artist artist=response.album;
                        if(artist!=null){
                            Log.d(TAG,"Name:"+artist.name);
                            Log.d(TAG,"Url:"+artist.url);
                            Log.d(TAG,"Mbid:"+artist.mbid);

                            List<Image> images=artist.image;
                            if(images!=null){
                                Log.d(TAG,Integer.toString(images.size()));
                                for(Image image:images){
                                    Log.d(TAG,image.text);
                                    Log.d(TAG,image.size);
                                }
                            }else{
                                Log.d(TAG,"Images are null");
                            }

                            Wiki wiki=artist.bio;
                            if(wiki!=null){
                                Log.d(TAG,wiki.content);
                                Log.d(TAG,wiki.published);
                                Log.d(TAG,wiki.summary);
                            }
                        }else{
                            Log.d(TAG,"This is null");
                        }
                    }
                });

    }
}

