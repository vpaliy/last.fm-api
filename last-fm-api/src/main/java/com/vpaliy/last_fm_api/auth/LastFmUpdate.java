package com.vpaliy.last_fm_api.auth;

import android.content.Context;
import com.vpaliy.last_fm_api.ServiceProvider;
import com.vpaliy.last_fm_api.model.Session;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import io.reactivex.Completable;

@SuppressWarnings({"unused", "WeakerAccess"})
public class LastFmUpdate extends ServiceProvider<UpdateService> {

    private static final String BASE_URL="https://ws.audioscrobbler.com/2.0/";

    private Session session;
    private Context context;

    public LastFmUpdate(Context context, Session session){
        super(session.apiKey,BASE_URL);
        this.session=session;
        this.context=context;
    }

    public Completable addTagsToAlbum(String artist, String album, String...tags){
        Map<String,String> options=postOptions("album.addTags");
        options.put("artist","artist");
        options.put("album",album);
        options.put("tags",convert(tags));
        return createService(context)
                .addTagsToAlbum(options);
    }

    public Completable  removeTagFromAlbum(String artist,String album, String tag){
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

    public Completable  addTagsToArtist(String artist, String...tags){
        Map<String,String> options=postOptions("artist.addTags");
        options.put("artist",artist);
        options.put("tags",convert(tags));
        return createService(context)
                .addTagsToArtist(options);
    }

    public Completable  removeTagFromArtist(String artist, String tag){
        Map<String,String> options=postOptions("artist.removeTag");
        options.put("artist",artist);
        options.put("tag",tag);
        return createService(context)
                .removeTagFromArtist(options);
    }


    public Completable  addTagsToTrack(String artist,String track, String...tags){
        Map<String,String> options=postOptions("track.addTags");
        options.put("artist",artist);
        options.put("track",track);
        options.put("tags",convert(tags));
        return createService(context)
                .addTagsToTrack(options);
    }

    public Completable  removeTagFromTrack(String artist, String track, String tag){
        Map<String,String> options=postOptions("track.removeTag");
        options.put("artist",artist);
        options.put("track",track);
        options.put("tag",tag);
        return createService(context)
                .removeTagFromTrack(options);
    }

    public Completable  loveTrack(String artist, String track){
        Map<String,String> options=postOptions("track.love");
        options.put("artist",artist);
        options.put("track",track);
        return createService(context)
                .loveTrack(options);
    }

    public Completable  unloveTrack(String artist, String track){
        Map<String,String> options=postOptions("track.unlove");
        options.put("artist",artist);
        options.put("track",track);
        return createService(context)
                .unloveTrack(options);
    }

    public Chain startChain(){
        return new Chain();
    }

    public Completable  updateNowPlayingTrack(String artist, String track){
        return null;
    }

    @Override
    protected Class<UpdateService> clazz() {
        return UpdateService.class;
    }

    public static LastFmUpdate create(Context context,Session session){
        return new LastFmUpdate(context,session);
    }

    public class Chain {
        private List<Completable> completables;

        private Chain(){
            completables=new LinkedList<>();
        }

        public Chain addTagsToAlbum(String artist, String album, String...tags){
            completables.add(LastFmUpdate.this.addTagsToAlbum(artist,album,tags));
            return this;
        }

        public Chain removeTagFromAlbum(String artist,String album, String tag){
            completables.add(LastFmUpdate.this.removeTagFromAlbum(artist,album,tag));
            return this;
        }

        public Chain addTagsToArtist(String artist, String...tags) {
            completables.add(LastFmUpdate.this.addTagsToArtist(artist,tags));
            return this;
        }

        public Chain removeTagFromArtist(String artist, String tag){
            completables.add(LastFmUpdate.this.removeTagFromArtist(artist,tag));
            return this;
        }

        public Chain addTagsToTrack(String artist,String track, String...tags){
            completables.add(LastFmUpdate.this.addTagsToTrack(artist,track,tags));
            return this;
        }

        public Chain removeTagFromTrack(String artist, String track, String tag){
            completables.add(LastFmUpdate.this.removeTagFromTrack(artist,track,tag));
            return this;
        }

        public Chain loveTrack(String artist, String track){
            completables.add(LastFmUpdate.this.loveTrack(artist,track));
            return this;
        }

        public Chain unloveTrack(String artist, String track){
            completables.add(LastFmUpdate.this.unloveTrack(artist,track));
            return this;
        }

        public Completable stop(){
            if(!completables.isEmpty()){
                Iterator<Completable> it=completables.iterator();
                Completable first=it.next();
                while(it.hasNext()){
                    Completable next=it.next();
                    first=first.andThen(next);
                }
                return first;
            }
            return Completable.error(new IllegalStateException("You haven't make any operations"));
        }
    }
}
