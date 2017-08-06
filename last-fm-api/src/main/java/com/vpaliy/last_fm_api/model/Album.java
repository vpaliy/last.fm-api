package com.vpaliy.last_fm_api.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import java.lang.reflect.Type;
import java.util.List;

public class Album {

    public String name;
    public String id;
    public String mbid;
    public String url;
    public String releaseDate;
    public int listeners;
    public int playcount;
    public Wiki wiki;
    public Wrapper<Tag> toptags;
    public Wrapper<Track> tracks;
    public List<Image> image;

    @JsonAdapter(Deserializer.class)
    public Artist artist;

    @SuppressWarnings("WeakerAccess")
    public class Wrapper<T> {
        @SerializedName(value = "tag",alternate = "track")
        public List<T> result;
    }


    public List<Tag> tags(){
        return toptags!=null?toptags.result:null;
    }

    public List<Track> tracks(){
        return tracks!=null?tracks.result:null;
    }


    private static class Deserializer implements JsonDeserializer<Artist> {
        @Override
        public Artist deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if(json.isJsonPrimitive()){
                Artist artist=new Artist();
                artist.name=json.getAsString();
                return artist;
            }
            return context.deserialize(json,typeOfT);
        }
    }

}
