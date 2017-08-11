package com.vpaliy.last_fm_api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LastFm {

    private static final String BASE_URL="http://ws.audioscrobbler.com/2.0/";
    private static final String API_QUERY = "api_key";

    private static final long CACHE_SIZE = 10 * 1024 * 1024;
    private static final int CONNECT_TIMEOUT = 15;
    private static final int WRITE_TIMEOUT = 60;
    private static final int READ_TIMEOUT = 60;

    private final String apiKey;

    public LastFm(String apiKey){
        this.apiKey=apiKey;
    }

    Interceptor provideOkHttpInterceptor(){
        return (chain -> {
            Request originalRequest = chain.request();
            HttpUrl originalHttpUrl = originalRequest.url();
            HttpUrl newHttpUrl = originalHttpUrl.newBuilder()
                    .setQueryParameter(API_QUERY, apiKey)
                    .build();
            Request newRequest = addHeader(originalRequest.newBuilder()
                    .url(newHttpUrl)).build();

            return chain.proceed(newRequest);});
    }

    private Request.Builder addHeader(Request.Builder builder){
        return builder;
    }

    OkHttpClient provideOkHttpClient(Context context, Interceptor interceptor) {
        Builder builder = new Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .cache(new Cache(context.getCacheDir(), CACHE_SIZE));

        return builder.build();
    }

    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .registerTypeAdapterFactory(new Adapter())
                .create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public LastFmService createService(Context context){
        OkHttpClient okHttpClient=provideOkHttpClient(context,provideOkHttpInterceptor());
        Retrofit retrofit=provideRetrofit(okHttpClient);
        return retrofit.create(LastFmService.class);
    }

    public static LastFm create(String apiKey){
        return new LastFm(apiKey);
    }


}
