package com.vpaliy.last_fm_api;


import com.vpaliy.last_fm_api.model.Album;
import com.vpaliy.last_fm_api.model.AlbumPage;
import com.vpaliy.last_fm_api.model.Artist;
import com.vpaliy.last_fm_api.model.ArtistPage;
import com.vpaliy.last_fm_api.model.Response;
import com.vpaliy.last_fm_api.model.Tag;
import com.vpaliy.last_fm_api.model.Track;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface LastFmService {

    @GET("?method=result.getinfo&format=json")
    Observable<Response<Album>> fetchAlbumInfo(@Query("artist") String artist, @Query("result") String album);

    @GET("?method=result.getTags&format=json")
    Observable<List<Tag>> fetchAlbumTags(@Query("artist") String artist, @Query("result") String album);

    @GET("/?method=chart.gettopartists&format=json")
    Observable<List<Artist>> fetchTopArtists(@Query("page") int page, @Query("limit") int limit);
    @GET("?method=chart.gettopartists&format=json")
    Observable<List<Artist>> fetchTopArtists();

    @GET("?method=chart.gettopartists&format=json")
    Observable<ArtistPage> fetchTopArtistIn();

    @GET("?method=result.getinfo&format=json")
    Observable<Response<Album>> fetchAlbumInfo(@Query("mbid") String id);

    @GET("?method=artist.gettopalbums&format=json")
    Observable<Response<AlbumPage>> fetchTopAlbums(@Query("artist") String artist);

    //tested
    @GET("?method=user.getweeklyalbumchart&format=json")
    Observable<Response<AlbumPage>> fetchWeeklyAlbumChart(@Query("user") String user);

    @GET("?method=artist.getinfo&format=json")
    Observable<Response<Artist>> fetchArtist(@Query("artist") String artist);

    @GET("?method=chart.gettopartists&format=json")
    Observable<Response<ArtistPage>> fetchTopArtistsChart(@Query("page") int page);

    @GET("?method=chart.gettopartists&format=json")
    Observable<Response<ArtistPage>> fetchTopArtistsChart(@Query("page") int page, @Query("limit") int limit);

    @GET("?method=track.getInfo&format=json")
    Observable<Response<Track>> fetchTrack(@Query("track") String track,
                                           @Query("artist") String artist);


}
