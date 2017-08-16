package com.vpaliy.last_fm_api.auth;

import io.reactivex.Completable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import com.vpaliy.last_fm_api.model.Status;
import java.util.Map;
import io.reactivex.Observable;

interface UpdateService {

    /* Album */

    @POST("./")
    @FormUrlEncoded
    Completable addTagsToAlbum(@FieldMap Map<String,String> options);

    @POST("./")
    @FormUrlEncoded
    Completable removeTagFromAlbum(@FieldMap Map<String,String> options);

      /* Artist */

    @POST("./")
    @FormUrlEncoded
    Completable addTagsToArtist(@FieldMap Map<String,String> options);

    @POST("./")
    @FormUrlEncoded
    Completable removeTagFromArtist(@FieldMap Map<String,String> options);

      /* Track */

    @POST("./")
    @FormUrlEncoded
    Completable addTagsToTrack(@FieldMap Map<String,String> options);

    @POST("./")
    @FormUrlEncoded
    Completable removeTagFromTrack(@FieldMap Map<String,String> options);

    @POST("./")
    @FormUrlEncoded
    Completable loveTrack(@FieldMap Map<String,String> options);

    @POST("./")
    @FormUrlEncoded
    Completable unloveTrack(@FieldMap Map<String,String> options);

    @POST("./")
    @FormUrlEncoded
    Completable updateNowPlayingTrack(@FieldMap Map<String,String> options);

}
