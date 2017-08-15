package com.vpaliy.last_fm_api.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Session {
    public String name;
    public String key;
    public String apiKey;
    public String apiSecret;
    public int subscriber;

    public static Session convertFromString(String sessionString){
        if(sessionString==null) return null;
        Type type=new TypeToken<Session>(){}.getType();
        Gson gson=new Gson();
        return gson.fromJson(sessionString,type);
    }

    public static String convertToString(Session session){
        if(session==null) return null;
        Type type=new TypeToken<Session>(){}.getType();
        Gson gson=new Gson();
        return gson.toJson(session,type);
    }
}
