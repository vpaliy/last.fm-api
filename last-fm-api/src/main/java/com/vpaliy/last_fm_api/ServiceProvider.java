package com.vpaliy.last_fm_api;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class ServiceProvider<T> {

    private static final String BASE_URL="http://ws.audioscrobbler.com/2.0/";
    private static final long CACHE_SIZE = 10 * 1024 * 1024;
    private static final int CONNECT_TIMEOUT = 15;
    private static final int WRITE_TIMEOUT = 60;
    private static final int READ_TIMEOUT = 60;
    private static final String API_QUERY = "api_key";

    protected String apiKey;
    private String baseUrl;


    public ServiceProvider(String apiKey){
        this(apiKey,BASE_URL);
    }

    public ServiceProvider(String apiKey, String baseUrl){
        if(TextUtils.isEmpty(apiKey)||TextUtils.isEmpty(baseUrl)){
            throw new IllegalArgumentException("ApiKey is null");
        }
        this.apiKey=apiKey;
        this.baseUrl=baseUrl;
    }

    private Interceptor buildOkHttpInterceptor(){
        return (chain -> {
            Request originalRequest = chain.request();
            HttpUrl originalHttpUrl = originalRequest.url();
            HttpUrl newHttpUrl = originalHttpUrl.newBuilder()
                    .addEncodedQueryParameter(API_QUERY, apiKey)
                    .build();
            Request newRequest = originalRequest.newBuilder()
                    .url(newHttpUrl).build();
            return chain.proceed(newRequest);});
    }

    private OkHttpClient provideOkHttpClient(Context context, Interceptor interceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .cache(new Cache(context.getCacheDir(), CACHE_SIZE));
        return builder.build();
    }

    private Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .registerTypeAdapterFactory(new Adapter())
                .create();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    protected abstract Class<T> clazz();

    protected T createService(Context context){
        return provideRetrofit(provideOkHttpClient(context,buildOkHttpInterceptor()))
                .create(clazz());
    }
}
