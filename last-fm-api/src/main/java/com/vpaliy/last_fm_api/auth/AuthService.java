package com.vpaliy.last_fm_api.auth;

import com.vpaliy.last_fm_api.model.Response;
import com.vpaliy.last_fm_api.model.Session;
import java.util.Map;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

interface AuthService {
    @POST("./")
    @FormUrlEncoded
    Observable<Response<Session>> auth(@FieldMap Map<String,String> map);
}