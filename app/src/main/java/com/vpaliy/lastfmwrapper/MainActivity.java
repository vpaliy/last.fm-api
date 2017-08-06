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

        service.fetchTopAlbums("Cher")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<AlbumWrapper>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG,"omCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Response<AlbumWrapper> response) {
                        Album album=response.album.album.get(0);
                        if(album!=null){
                            Log.d(TAG,"Name:"+album.name);
                            Log.d(TAG,"Artist:"+album.artist.name);
                            Log.d(TAG,"Artist:"+album.artist.url);
                            Log.d(TAG,"Artist:"+album.artist.mbid);
                            Log.d(TAG,"Id:"+album.id);
                            Log.d(TAG,"Id:"+album.mbid);
                            Log.d(TAG,"Url:"+album.url);

                            List<Image> images=album.image;
                            if(images!=null){
                                Log.d(TAG,Integer.toString(images.size()));
                                for(Image image:images){
                                    Log.d(TAG,image.text);
                                    Log.d(TAG,image.size);
                                }
                            }else{
                                Log.d(TAG,"Images are null");
                            }

                            Wiki wiki=album.wiki;
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

