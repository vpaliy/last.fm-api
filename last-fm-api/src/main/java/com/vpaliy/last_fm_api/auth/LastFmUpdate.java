package com.vpaliy.last_fm_api.auth;

import android.content.Context;
import com.vpaliy.last_fm_api.ServiceProvider;
import com.vpaliy.last_fm_api.model.Session;
import com.vpaliy.last_fm_api.model.Status;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import rx.Observable;

@SuppressWarnings({"unused", "WeakerAccess"})
public class LastFmUpdate extends ServiceProvider<UpdateService> {

    private static final String BASE_URL="https://ws.audioscrobbler.com/2.0/";

    private Session session;
    private Context context;

    public LastFmUpdate(Context context, Session session){
        super(session.apiKey,BASE_URL);
        this.session=session;
    }

    public Observable<Status> addTagsToAlbum(String artist,String album, String...tags){
        Map<String,String> options=postOptions("album.addTags");
        options.put("artist","artist");
        options.put("album",album);
        options.put("tags",convert(tags));
        return createService(context)
                .addTagsToAlbum(options);
    }

    public Observable<Status> removeTagFromAlbum(String artist,String album, String tag){
        Map<String,String> options=postOptions("album.removeTag");
        options.put("artist",artist);
        options.put("album",album);
        options.put("tag",tag);
        return createService(context)
                .removeTagFromAlbum(options);
    }

    private Map<String,String> postOptions(String method){
        Map<String,String> options=new HashMap<>();
        options.put("method",method);
        options.put("format","json");
        options.put("sk",session.key);
        return options;
    }
    @SuppressWarnings("all")
    private <T> String convert(T...strings){
        return Arrays.toString(strings)
                .replaceAll("[\\[.\\].\\s+]", "");
    }

    public Observable<Status> addTagsToArtist(String artist, String...tags){
        Map<String,String> options=postOptions("artist.addTags");
        options.put("artist",artist);
        options.put("tags",convert(tags));
        return createService(context)
                .addTagsToArtist(options);
    }

    public Observable<Status> removeTagFromArtist(String artist, String tag){
        Map<String,String> options=postOptions("artist.removeTag");
        options.put("artist",artist);
        options.put("tag",tag);
        return createService(context)
                .removeTagFromArtist(options);
    }


    public Observable<Status> addTagsToTrack(String artist,String track, String...tags){
        Map<String,String> options=postOptions("track.addTags");
        options.put("artist",artist);
        options.put("track",track);
        options.put("tags",convert(tags));
        return createService(context)
                .addTagsToTrack(options);
    }

    public Observable<Status> removeTagFromTrack(String artist, String track, String tag){
        Map<String,String> options=postOptions("track.removeTag");
        options.put("artist",artist);
        options.put("track",track);
        options.put("tag",tag);
        return createService(context)
                .removeTagFromTrack(options);
    }

    public Observable<Status> loveTrack(String artist, String track){
        Map<String,String> options=postOptions("track.love");
        options.put("artist",artist);
        options.put("track",track);
        return createService(context)
                .loveTrack(options);
    }

    public Observable<Status> unloveTrack(String artist, String track){
        Map<String,String> options=postOptions("track.unlove");
        options.put("artist",artist);
        options.put("track",track);
        return createService(context)
                .unloveTrack(options);
    }

    public Observable<Status> updateNowPlayingTrack(String artist, String track){
        return null;
    }

    @Override
    protected Class<UpdateService> clazz() {
        return UpdateService.class;
    }

    public static LastFmUpdate create(Context context,Session session){
        return new LastFmUpdate(context,session);
    }
}
