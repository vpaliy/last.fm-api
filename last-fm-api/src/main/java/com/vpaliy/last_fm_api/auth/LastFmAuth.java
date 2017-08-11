package com.vpaliy.last_fm_api.auth;

import android.text.TextUtils;
import android.util.Log;

import com.vpaliy.last_fm_api.model.Session;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

@SuppressWarnings({"unused","WeakerAccess"})
public class LastFmAuth {

    private String apiKey;

    public LastFmAuth(String apiKey){
        if(TextUtils.isEmpty(apiKey)){
            throw new IllegalArgumentException("ApiKey is null");
        }
        this.apiKey=apiKey;
    }

    private Interceptor buildInterceptor(){
        return (chain -> {
            Request originalRequest = chain.request();
            HttpUrl originalHttpUrl = originalRequest.url();
            HttpUrl newHttpUrl = originalHttpUrl.newBuilder()
                    .setQueryParameter("api_key", apiKey)
                    .build();
            Request newRequest = originalRequest.newBuilder()
                    .url(newHttpUrl).build();
            return chain.proceed(newRequest);});
    }

    private Retrofit buildRetrofit(){
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(buildInterceptor())
                .build();
        return new Retrofit.Builder()
                .baseUrl("http://ws.audioscrobbler.com/2.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public Observable<Session> auth(String username,String password){
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            throw new IllegalArgumentException("username or password is empty");
        }
        String signature=generateSignature(username,password);
        Log.d(LastFmAuth.class.getSimpleName(),signature);
        Map<String,String> options=new HashMap<>();
        options.put("username",username);
        options.put("password",password);
        options.put("api_key",apiKey);
        options.put("api_sig",signature);
        return buildRetrofit().create(AuthService.class)
                .auth(options);
    }

    private String generateSignature(String username, String password){
        String passwordLabel="password"+password;
        String usernameLabel="username"+username;
        String apiKeyLabel="api_key"+apiKey;
        String methodLabel="methodauth.getMobileSession";
        return md5(apiKeyLabel+methodLabel+passwordLabel+usernameLabel);
    }

    private String md5(String s) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static LastFmAuth create(String apiKey){
        return new LastFmAuth(apiKey);
    }
}
