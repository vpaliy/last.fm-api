package com.vpaliy.last_fm_api.auth;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import com.vpaliy.last_fm_api.model.Status;
import java.util.Map;
import rx.Observable;

interface UpdateService {

    /* Album */

    @POST("./")
    @FormUrlEncoded
    Observable<Status> addTagsToAlbum(@FieldMap Map<String,String> options);

    @POST("./")
    @FormUrlEncoded
    Observable<Status> removeTagFromAlbum(@FieldMap Map<String,String> options);

      /* Artist */

    @POST("./")
    @FormUrlEncoded
    Observable<Status> addTagsToArtist(@FieldMap Map<String,String> options);

    @POST("./")
    @FormUrlEncoded
    Observable<Status> removeTagFromArtist(@FieldMap Map<String,String> options);

      /* Track */

    @POST("./")
    @FormUrlEncoded
    Observable<Status> addTagsToTrack(@FieldMap Map<String,String> options);

    @POST("./")
    @FormUrlEncoded
    Observable<Status> removeTagFromTrack(@FieldMap Map<String,String> options);

    @POST("./")
    @FormUrlEncoded
    Observable<Status> loveTrack(@FieldMap Map<String,String> options);

    @POST("./")
    @FormUrlEncoded
    Observable<Status> unloveTrack(@FieldMap Map<String,String> options);

    @POST("./")
    @FormUrlEncoded
    Observable<Status> updateNowPlayingTrack(@FieldMap Map<String,String> options);

}
