package com.vpaliy.lastfmwrapper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.vpaliy.last_fm_api.LastFmApi;
import com.vpaliy.last_fm_api.model.Artist;
import com.vpaliy.last_fm_api.model.Image;
import com.vpaliy.last_fm_api.model.Response;
import com.vpaliy.last_fm_api.model.Tag;
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

        LastFmApi.create(Config.API_KEY)
                .createService(this)
                .fetchArtist("Cher")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<Artist>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG,"onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Response<Artist> response) {
                        Artist artist=response.result;
                        if(artist!=null){
                            Log.d(TAG,"Full name:"+artist.name);
                            Log.d(TAG,"Id:"+artist.mbid);
                            Log.d(TAG,"Url:"+artist.url);
                            Log.d(TAG,"Streamable:"+artist.streamable);
                            List<Image> images=artist.image;
                            if(images!=null){
                                for(Image image:images){
                                    Log.d(TAG,"Size:"+image.size);
                                    Log.d(TAG,"Url:"+image.text);
                                }
                            }else{
                                Log.d(TAG,"Is null:");
                            }
                            Log.d(TAG,"Listeners:"+artist.listeners);
                            Log.d(TAG,"Playcount:"+artist.playcount);
                            Log.d(TAG,"Plays:"+artist.plays);
                            List<Artist> similar=artist.similar();
                            if(similar!=null){
                                for(Artist art:similar){
                                    Log.d(TAG,"Name:"+art.name);
                                }
                            }else{
                                Log.d(TAG,"Is null:");
                            }
                            List<Tag> tags=artist.tags();
                            if(tags!=null){
                                Log.d(TAG,Integer.toString(tags.size()));
                            }
                        }else{
                            Log.d(TAG,"Result is null");
                        }
                    }
                });
    }
}

