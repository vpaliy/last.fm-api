package com.vpaliy.last_fm_api;

import com.vpaliy.last_fm_api.model.Album;
import com.vpaliy.last_fm_api.model.AlbumPage;
import com.vpaliy.last_fm_api.model.Artist;
import com.vpaliy.last_fm_api.model.ArtistPage;
import com.vpaliy.last_fm_api.model.Response;
import com.vpaliy.last_fm_api.model.TagPage;
import com.vpaliy.last_fm_api.model.TrackPage;
import java.util.Map;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface LastFmService {

    @GET(Endpoints.ALBUM_INFO)
    Observable<Response<Album>> fetchAlbumInfo(@Query("artist") String artist,
                                               @Query("album") String album);
    @GET(Endpoints.ALBUM_INFO)
    Observable<Response<Album>> fetchAlbumInfo(@Query("mbid") String mbid);

    @GET(Endpoints.ALBUM_INFO)
    Observable<Response<Album>> fetchAlbumInfo(@QueryMap Map<String,Object> options);

    @GET(Endpoints.ALBUM_TAGS)
    Observable<Response<TagPage>> fetchAlbumTags(@Query("artist") String artist,
                                                 @Query("album") String album);
    @GET(Endpoints.ALBUM_TAGS)
    Observable<Response<TagPage>> fetchAlbumTags(@QueryMap Map<String,Object> options);

    @GET(Endpoints.ARTIST_INFO)
    Observable<Response<Artist>> fetchArtist(@Query("artist") String artist);

    @GET(Endpoints.ARTIST_INFO)
    Observable<Response<Artist>> fetchArtistById(@Query("mbid") String mbid);

    @GET(Endpoints.ARTIST_INFO)
    Observable<Response<Artist>> fetchArtist(@QueryMap Map<String,Object> options);

    @GET(Endpoints.ARTIST_SIMILAR)
    Observable<Response<ArtistPage>> fetchSimilarArtists(@Query("artist") String artist);

    @GET(Endpoints.ARTIST_SIMILAR)
    Observable<Response<ArtistPage>> fetchSimilarArtistsById(@Query("mbid") String mbid);

    @GET(Endpoints.ARTIST_SIMILAR)
    Observable<Response<ArtistPage>> fetchSimilarArtists(@QueryMap Map<String,Object> options);

    @GET(Endpoints.ARTIST_TAGS)
    Observable<Response<TagPage>> fetchArtistTags(@Query("artist") String artist);

    @GET(Endpoints.ARTIST_TAGS)
    Observable<Response<TagPage>> fetchArtistTagsById(@Query("mbid") String mbid);

    @GET(Endpoints.ARTIST_TAGS)
    Observable<Response<TagPage>> fetchArtistTags(@QueryMap Map<String,Object> options);

    @GET(Endpoints.ARTIST_TOP_ALBUMS)
    Observable<Response<AlbumPage>> fetchArtistTopAlbums(@Query("artist") String artist);

    @GET(Endpoints.ARTIST_TOP_ALBUMS)
    Observable<Response<AlbumPage>> fetchArtistTopAlbumsById(@Query("mbid") String mbid);

    @GET(Endpoints.ARTIST_TOP_ALBUMS)
    Observable<Response<AlbumPage>> fetchArtistTopAlbums(@QueryMap Map<String, Object> options);

    @GET(Endpoints.ARTIST_TOP_TAGS)
    Observable<Response<TagPage>> fetchArtistTopTags(@Query("artist") String artist);

    @GET(Endpoints.ARTIST_TOP_TAGS)
    Observable<Response<TagPage>> fetchArtistsTopTagsById(@Query("mbid") String mbid);

    @GET(Endpoints.ARTIST_TOP_TAGS)
    Observable<Response<TagPage>> fetchArtistsTopTags(@QueryMap Map<String,Object> options);

    @GET(Endpoints.ARTIST_TOP_TRACKS)
    Observable<Response<TrackPage>> fetchArtistTopTracks(@Query("artist") String artist);

    @GET(Endpoints.ARTIST_TOP_TRACKS)
    Observable<Response<TrackPage>> fetchArtistTopTracksById(@Query("mbid") String mbid);

    @GET(Endpoints.ARTIST_TOP_TRACKS)
    Observable<Response<TrackPage>> fetchArtistTopTracks(@QueryMap Map<String,Object> options);
}
