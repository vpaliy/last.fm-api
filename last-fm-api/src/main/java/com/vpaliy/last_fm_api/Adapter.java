package com.vpaliy.last_fm_api;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.vpaliy.last_fm_api.model.ArtistWrapper;
import com.vpaliy.last_fm_api.model.Response;
import com.vpaliy.last_fm_api.model.Wrapper;

import java.lang.reflect.Type;

public class Adapter implements JsonDeserializer<Response<? extends Wrapper>> {

    @Override
    public Response<? extends Wrapper> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }
}
