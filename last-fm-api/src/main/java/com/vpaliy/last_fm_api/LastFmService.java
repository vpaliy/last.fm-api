package com.vpaliy.last_fm_api;


import com.vpaliy.last_fm_api.model.Album;
import com.vpaliy.last_fm_api.model.AlbumWrapper;
import com.vpaliy.last_fm_api.model.Artist;
import com.vpaliy.last_fm_api.model.ArtistWrapper;
import com.vpaliy.last_fm_api.model.Response;
import com.vpaliy.last_fm_api.model.Tag;
import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface LastFmService {

    @GET("?method=album.getinfo&format=json")
    Observable<Response<Album>> fetchAlbumInfo(@Query("artist") String artist,
                                        @Query("album") String album);

    @GET("?method=album.getTags&format=json")
    Observable<List<Tag>> fetchAlbumTags(@Query("artist") String artist,
                                         @Query("album") String album);

    @GET("/?method=chart.gettopartists&format=json")
    Observable<List<Artist>> fetchTopArtists(@Query("page") int page,
                                             @Query("limit") int limit);
    @GET("?method=chart.gettopartists&format=json")
    Observable<List<Artist>> fetchTopArtists();

    @GET("?method=chart.gettopartists&format=json")
    Observable<ArtistWrapper> fetchTopArtistIn();

    @GET("?method=album.getinfo&format=json")
    Observable<Album> fetchAlbumInfo(@Query("mbid") String id);

    @GET("?method=artist.gettopalbums&format=json")
    Observable<Response<AlbumWrapper>> fetchTopAlbums(@Query("artist") String artist);

}
