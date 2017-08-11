package com.vpaliy.last_fm_api.auth;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vpaliy.last_fm_api.model.Response;
import com.vpaliy.last_fm_api.model.Session;
import java.io.UnsupportedEncodingException;
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
    private String apiSecret;

    public LastFmAuth(String apiKey, String apiSecret){
        if(TextUtils.isEmpty(apiKey)||TextUtils.isEmpty(apiSecret)){
            throw new IllegalArgumentException("ApiKey or ApiSecret is null");
        }
        this.apiKey=apiKey;
        this.apiSecret=apiSecret;
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

    public Observable<Response<Session>> auth(String username, String password){
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            throw new IllegalArgumentException("username or password is empty");
        }
        String signature=generateSignature(username,password);
        Map<String,String> options=new HashMap<>();
        options.put("method","auth.getMobileSession");
        options.put("format","json");
        options.put("api_key",apiKey);
        options.put("api_sig",signature);
        options.put("username",username);
        options.put("password",password);
        return buildRetrofit().create(AuthService.class)
                .auth(options)
                .map(response -> {
                    if(response!=null){
                        if(response.result!=null){
                            response.result.apiKey=apiKey;
                            response.result.apiSecret=apiSecret;
                        }
                    }
                    return response;
                });
    }

    private String generateSignature(String username, String password){
        String passwordLabel="password"+password;
        String usernameLabel="username"+username;
        String apiKeyLabel="api_key"+apiKey;
        String methodLabel="methodauth.getMobileSession";
        String secret=apiSecret;
        return generateMD5(apiKeyLabel+methodLabel+passwordLabel+usernameLabel+secret);
    }

    private static String generateMD5(String in) {
        try {
            byte[] bytesOfMessage = in.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(bytesOfMessage);
            String out = "";
            for (byte symbol : digest) {
                out += String.format("%02X", symbol);
            }
            return out;
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ignored) {
            return null;
        }
    }
    public static LastFmAuth create(String apiKey, String apiSecret){
        return new LastFmAuth(apiKey,apiSecret);
    }
}
