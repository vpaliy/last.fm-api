package com.vpaliy.last_fm_api.auth;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vpaliy.last_fm_api.model.Session;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings({"unused", "WeakerAccess"})
public class LastFmUpdate {

    private Session session;

    public LastFmUpdate(Session session){
        if(session==null|| TextUtils.isEmpty(session.apiKey) ||
                TextUtils.isEmpty(session.apiSecret) || TextUtils.isEmpty(session.key)){
            throw new IllegalArgumentException("Required arguments are null");
        }
        this.session=session;
    }


    private Interceptor buildInterceptor(){
        return (chain -> {
            Request originalRequest = chain.request();
            HttpUrl originalHttpUrl = originalRequest.url();
            HttpUrl newHttpUrl = originalHttpUrl.newBuilder()
                    .build();
            Request newRequest = originalRequest.newBuilder()
                    .url(newHttpUrl).build();
            return chain.proceed(newRequest);});
    }

    private Retrofit buildRetrofit(){
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(buildInterceptor())
                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return new Retrofit.Builder()
                .baseUrl("https://ws.audioscrobbler.com/2.0/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }


    public static LastFmUpdate create(Session session){
        return new LastFmUpdate(session);
    }
}
