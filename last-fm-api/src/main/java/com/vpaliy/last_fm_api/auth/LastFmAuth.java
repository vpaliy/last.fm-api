package com.vpaliy.last_fm_api.auth;

import android.content.Context;
import android.text.TextUtils;
import com.vpaliy.last_fm_api.ServiceProvider;
import com.vpaliy.last_fm_api.model.Response;
import com.vpaliy.last_fm_api.model.Session;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import rx.Observable;

@SuppressWarnings({"unused","WeakerAccess"})
public class LastFmAuth extends ServiceProvider<AuthService> {

    private static final String BASE_URL="https://ws.audioscrobbler.com/2.0/";
    private String apiSecret;

    public LastFmAuth(String apiKey, String apiSecret){
        super(apiKey,BASE_URL);
        this.apiSecret=apiSecret;
    }

    @Override
    protected Class<AuthService> clazz() {
        return AuthService.class;
    }

    public Observable<Response<Session>> auth(Context context, String username, String password){
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            throw new IllegalArgumentException("username or password is empty");
        }
        String signature=generateSignature(username,password);
        Map<String,String> options=new HashMap<>();
        options.put("method","auth.getMobileSession");
        options.put("format","json");
        options.put("api_sig",signature);
        options.put("username",username);
        options.put("password",password);
        return createService(context)
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
