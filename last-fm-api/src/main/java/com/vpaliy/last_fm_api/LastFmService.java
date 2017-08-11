package com.vpaliy.last_fm_api;

import com.vpaliy.last_fm_api.model.Album;
import com.vpaliy.last_fm_api.model.AlbumPage;
import com.vpaliy.last_fm_api.model.Artist;
import com.vpaliy.last_fm_api.model.ArtistPage;
import com.vpaliy.last_fm_api.model.Chart;
import com.vpaliy.last_fm_api.model.Response;
import com.vpaliy.last_fm_api.model.Tag;
import com.vpaliy.last_fm_api.model.TagPage;
import com.vpaliy.last_fm_api.model.TaggingPage;
import com.vpaliy.last_fm_api.model.Track;
import com.vpaliy.last_fm_api.model.TrackPage;
import com.vpaliy.last_fm_api.model.User;
import com.vpaliy.last_fm_api.model.UserPage;

import java.util.Map;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

@SuppressWarnings("unused")
public interface LastFmService {

    @GET(Endpoints.ALBUM_INFO)
    Observable<Response<Album>> fetchAlbumInfo(@Query("artist") String artist,
                                               @Query("album") String album);
    @GET(Endpoints.ALBUM_INFO)
    Observable<Response<Album>> fetchAlbumInfo(@Query("mbid") String mbid);

    @GET(Endpoints.ALBUM_INFO)
    Observable<Response<Album>> fetchAlbumInfo(@Query("mbid") String mbid,
                                               @QueryMap Map<String,Object> options);
    @GET(Endpoints.ALBUM_INFO)
    Observable<Response<Album>> fetchAlbumInfo(@Query("artist") String artist,
                                               @Query("album") String album,
                                               @QueryMap Map<String,Object> options);
    @GET(Endpoints.ALBUM_TAGS)
    Observable<Response<TagPage>> fetchAlbumTags(@Query("artist") String artist,
                                                 @Query("album") String album);
    @GET(Endpoints.ALBUM_TAGS)
    Observable<Response<TagPage>> fetchAlbumTags(@Query("artist") String artist,
                                                 @Query("album") String album,
                                                 @QueryMap Map<String,Object> options);
    @GET(Endpoints.ARTIST_INFO)
    Observable<Response<Artist>> fetchArtist(@Query("artist") String artist);

    @GET(Endpoints.ARTIST_INFO)
    Observable<Response<Artist>> fetchArtistById(@Query("mbid") String mbid);

    @GET(Endpoints.ARTIST_INFO)
    Observable<Response<Artist>> fetchArtist(@Query("artist") String artist,
                                             @QueryMap Map<String,Object> options);
    @GET(Endpoints.ARTIST_INFO)
    Observable<Response<Artist>> fetchArtistById(@Query("mbid") String mbid,
                                                 @QueryMap Map<String,Object> options);

    @GET(Endpoints.ARTIST_SIMILAR)
    Observable<Response<ArtistPage>> fetchSimilarArtists(@Query("artist") String artist);

    @GET(Endpoints.ARTIST_SIMILAR)
    Observable<Response<ArtistPage>> fetchSimilarArtistsById(@Query("mbid") String mbid);

    @GET(Endpoints.ARTIST_SIMILAR)
    Observable<Response<ArtistPage>> fetchSimilarArtists(@Query("artist") String artist,
                                                         @QueryMap Map<String,Object> options);
    @GET(Endpoints.ARTIST_SIMILAR)
    Observable<Response<ArtistPage>> fetchSimilarArtistsById(@Query("mbid") String mbid,
                                                             @QueryMap Map<String,Object> options);
    @GET(Endpoints.ARTIST_TAGS)
    Observable<Response<TagPage>> fetchArtistTags(@Query("artist") String artist);

    @GET(Endpoints.ARTIST_TAGS)
    Observable<Response<TagPage>> fetchArtistTagsById(@Query("mbid") String mbid);

    @GET(Endpoints.ARTIST_TAGS)
    Observable<Response<TagPage>> fetchArtistTags(@Query("artist") String artist,
                                                  @QueryMap Map<String,Object> options);
    @GET(Endpoints.ARTIST_TAGS)
    Observable<Response<TagPage>> fetchArtistTagsById(@Query("mbid") String mbid,
                                                      @QueryMap Map<String,Object> options);

    @GET(Endpoints.ARTIST_TOP_ALBUMS)
    Observable<Response<AlbumPage>> fetchArtistTopAlbums(@Query("artist") String artist);

    @GET(Endpoints.ARTIST_TOP_ALBUMS)
    Observable<Response<AlbumPage>> fetchArtistTopAlbumsById(@Query("mbid") String mbid);

    @GET(Endpoints.ARTIST_TOP_ALBUMS)
    Observable<Response<AlbumPage>> fetchArtistTopAlbums(@Query("artist") String artist,
                                                         @QueryMap Map<String, Object> options);
    @GET(Endpoints.ARTIST_TOP_ALBUMS)
    Observable<Response<AlbumPage>> fetchArtistTopAlbumsById(@Query("mbid") String mbid,
                                                             @QueryMap Map<String, Object> options);
    @GET(Endpoints.ARTIST_TOP_TAGS)
    Observable<Response<TagPage>> fetchArtistTopTags(@Query("artist") String artist);

    @GET(Endpoints.ARTIST_TOP_TAGS)
    Observable<Response<TagPage>> fetchArtistsTopTagsById(@Query("mbid") String mbid);

    @GET(Endpoints.ARTIST_TOP_TAGS)
    Observable<Response<TagPage>> fetchArtistsTopTags(@Query("artist") String artist,
                                                      @QueryMap Map<String,Object> options);
    @GET(Endpoints.ARTIST_TOP_TAGS)
    Observable<Response<TagPage>> fetchArtistsTopTagsById(@Query("mbid") String mbid,
                                                          @QueryMap Map<String,Object> options);

    @GET(Endpoints.ARTIST_TOP_TRACKS)
    Observable<Response<TrackPage>> fetchArtistTopTracks(@Query("artist") String artist);

    @GET(Endpoints.ARTIST_TOP_TRACKS)
    Observable<Response<TrackPage>> fetchArtistTopTracksById(@Query("mbid") String mbid);

    @GET(Endpoints.ARTIST_TOP_TRACKS)
    Observable<Response<TrackPage>> fetchArtistTopTracks(@Query("artist") String artist,
                                                         @QueryMap Map<String,Object> options);
    @GET(Endpoints.ARTIST_TOP_TRACKS)
    Observable<Response<TrackPage>> fetchArtistTopTracksById(@Query("mbid") String mbid,
                                                             @QueryMap Map<String,Object> options);
    @GET(Endpoints.CHART_TOP_ARTISTS)
    Observable<Response<ArtistPage>> fetchChartTopArtists(@Query("page") int page,
                                                          @Query("limit") int limit);
    @GET(Endpoints.CHART_TOP_ARTISTS)
    Observable<Response<ArtistPage>> fetchChartTopArtists(@Query("page") int page);

    @GET(Endpoints.CHART_TOP_TAGS)
    Observable<Response<TagPage>> fetchChartTopTags(@Query("page") int page,
                                                    @Query("limit") int limit);
    @GET(Endpoints.CHART_TOP_TAGS)
    Observable<Response<TagPage>> fetchChartTopTags(@Query("page") int page);

    @GET(Endpoints.CHART_TOP_TRACKS)
    Observable<Response<TrackPage>> fetchChartTopTracks(@Query("page") int page,
                                                        @Query("limit") int limit);
    @GET(Endpoints.CHART_TOP_TRACKS)
    Observable<Response<TrackPage>> fetchChartTopTracks(@Query("page") int page);

    @GET(Endpoints.GEO_TOP_ARTISTS)
    Observable<Response<ArtistPage>> fetchGeoTopArtists(@Query("country") String country);

    @GET(Endpoints.GEO_TOP_ARTISTS)
    Observable<Response<ArtistPage>> fetchGeoTopArtists(@Query("country") String country,
                                                        @QueryMap Map<String,Object> options);
    @GET(Endpoints.GEO_TOP_TRACKS)
    Observable<Response<TrackPage>> fetchGeoTopTracks(@Query("country") String country);

    @GET(Endpoints.GEO_TOP_TRACKS)
    Observable<Response<TrackPage>> fetchGeoTopTracks(@Query("country") String country,
                                                      @QueryMap Map<String,Object> options);
    @GET(Endpoints.LIBRARY_ARTISTS)
    Observable<Response<ArtistPage>> fetchLibraryArtists(@Query("user") String user);

    @GET(Endpoints.LIBRARY_ARTISTS)
    Observable<Response<ArtistPage>> fetchLibraryArtists(@Query("user") String user,
                                                         @QueryMap Map<String,Object> options);
    @GET(Endpoints.TAG_INFO)
    Observable<Response<Tag>> fetchTagInfo(@Query("tag") String tag);

    @GET(Endpoints.TAG_INFO)
    Observable<Response<Tag>> fetchTagInfo(@Query("tag") String tag,
                                          @QueryMap Map<String,Object> options);
    @GET(Endpoints.TAG_SIMILAR)
    Observable<Response<TagPage>> fetchSimilarTags(@Query("tag") String tag);

    @GET(Endpoints.TAG_TOP_ALBUMS)
    Observable<Response<AlbumPage>> fetchTagTopAlbums(@Query("tag") String tag);

    @GET(Endpoints.TAG_TOP_ALBUMS)
    Observable<Response<AlbumPage>> fetchTagTopAlbums(@Query("tag") String tag,
                                                      @QueryMap Map<String,Object> options);
    @GET(Endpoints.TAG_TOP_ARTISTS)
    Observable<Response<ArtistPage>> fetchTagTopArtists(@Query("tag") String tag,
                                                        @QueryMap Map<String,Object> options);
    @GET(Endpoints.TAG_TOP_TAGS)
    Observable<Response<TagPage>> fetchTopTags();

    @GET(Endpoints.TAG_TOP_TRACKS)
    Observable<Response<TrackPage>> fetchTagTopTracks(@Query("tag") String tag);

    @GET(Endpoints.TAG_TOP_TRACKS)
    Observable<Response<TrackPage>> fetchTagTopTracks(@Query("tag") String tag,
                                                      @QueryMap Map<String,Object> options);
    @GET(Endpoints.TAG_WEEKLY_CHART)
    Observable<Response<Chart>> fetchTagWeeklyChart(@Query("tag") String tag);

    @GET(Endpoints.TRACK_INFO)
    Observable<Response<Track>> fetchTrackInfo(@Query("mbid") String mbid);

    @GET(Endpoints.TRACK_INFO)
    Observable<Response<Track>> fetchTrackInfo(@Query("mbid") String mbid,
                                               @QueryMap Map<String,Object> options);

    @GET(Endpoints.TRACK_INFO)
    Observable<Response<Track>> fetchTrackInfo(@Query("track") String track,
                                               @Query("artist") String artist);
    @GET(Endpoints.TRACK_INFO)
    Observable<Response<Track>> fetchTrackInfo(@Query("track") String track,
                                               @Query("artist") String artist,
                                               @QueryMap Map<String,Object> options);
    @GET(Endpoints.TRACK_SIMILAR)
    Observable<Response<TrackPage>> fetchTrackSimilar(@Query("track") String track,
                                                      @Query("artist") String artist);
    @GET(Endpoints.TRACK_SIMILAR)
    Observable<Response<TrackPage>> fetchTrackSimilar(@Query("track") String track,
                                                      @Query("artist") String artist,
                                                      @QueryMap Map<String,Object> options);
    @GET(Endpoints.TRACK_SIMILAR)
    Observable<Response<TrackPage>> fetchTrackSimilar(@Query("mbid") String mbid,
                                                      @QueryMap Map<String,Object> options);
    @GET(Endpoints.TRACK_SIMILAR)
    Observable<Response<TrackPage>> fetchTrackSimilar(@Query("mbid") String mbid);

    @GET(Endpoints.TRACK_TAGS)
    Observable<Response<TagPage>> fetchTrackTags(@Query("mbid") String mbid);

    @GET(Endpoints.TRACK_TAGS)
    Observable<Response<TagPage>> fetchTrackTags(@Query("mbid") String mbid,
                                                 @QueryMap Map<String,Object> options);
    @GET(Endpoints.TRACK_TAGS)
    Observable<Response<TagPage>> fetchTrackTags(@Query("track") String track,
                                                 @Query("artist") String artist,
                                                 @QueryMap Map<String,Object> options);
    @GET(Endpoints.TRACK_TAGS)
    Observable<Response<TagPage>> fetchTrackTags(@Query("track") String track,
                                                 @Query("artist") String artist);

    @GET(Endpoints.TRACK_TOP_TAGS)
    Observable<Response<TagPage>> fetchTrackTopTags(@Query("mbid") String mbid);

    @GET(Endpoints.TRACK_TOP_TAGS)
    Observable<Response<TagPage>> fetchTrackTopTags(@Query("mbid") String mbid,
                                                 @QueryMap Map<String,Object> options);
    @GET(Endpoints.TRACK_TOP_TAGS)
    Observable<Response<TagPage>> fetchTrackTopTags(@Query("track") String track,
                                                 @Query("artist") String artist,
                                                 @QueryMap Map<String,Object> options);
    @GET(Endpoints.TRACK_TOP_TAGS)
    Observable<Response<TagPage>> fetchTrackTopTags(@Query("track") String track,
                                                    @Query("artist") String artist);

    @GET(Endpoints.USER_INFO)
    Observable<Response<User>> fetchUserInfo(@Query("user") String user);

    @GET(Endpoints.USER_ARTIST_TRACKS)
    Observable<Response<TrackPage>> fetchUserArtistTracks(@Query("user") String user,
                                                          @Query("artist") String artist);
    @GET(Endpoints.USER_ARTIST_TRACKS)
    Observable<Response<TrackPage>> fetchUserArtistTracks(@Query("user") String user,
                                                          @Query("artist") String artist,
                                                          @QueryMap Map<String,Object> options);
    @GET(Endpoints.USER_FRIENDS)
    Observable<Response<UserPage>> fetchUserFriends(@Query("user") String user);

    @GET(Endpoints.USER_FRIENDS)
    Observable<Response<UserPage>> fetchUserFriends(@Query("user") String user,
                                                    @QueryMap Map<String,Object> options);

    @GET(Endpoints.USER_LOVED_TRACKS)
    Observable<Response<TrackPage>> fetchUserLovedTracks(@Query("user") String name);

    @GET(Endpoints.USER_LOVED_TRACKS)
    Observable<Response<TrackPage>> fetchUserLovedTracks(@Query("user") String name,
                                                         @QueryMap Map<String,Object> options);
    @GET(Endpoints.USER_PERSONAL_TAGS)
    Observable<Response<TaggingPage>> fetchUserPersonalTags(@Query("user") String user,
                                                        @Query("tag") String tag,
                                                        @Query("taggingtype") String taggingType);

    @GET(Endpoints.USER_PERSONAL_TAGS)
    Observable<Response<TaggingPage>> fetchUserPersonalTags(@Query("user") String user,
                                                            @Query("tag") String tag,
                                                            @Query("taggingtype") String taggingType,
                                                            @QueryMap Map<String,Object> options);
    @GET(Endpoints.USER_RECENT_TRACKS)
    Observable<Response<TrackPage>> fetchUserRecentTracks(@Query("user") String user);

    @GET(Endpoints.USER_RECENT_TRACKS)
    Observable<Response<TrackPage>> fetchUserRecentTracks(@Query("user") String user,
                                                          @QueryMap Map<String,Object> options);
    @GET(Endpoints.USER_TOP_ALBUMS)
    Observable<Response<AlbumPage>> fetchUserTopAlbums(@Query("user") String user);

    @GET(Endpoints.USER_TOP_ALBUMS)
    Observable<Response<AlbumPage>> fetchUserTopAlbums(@Query("user") String user,
                                                       @QueryMap Map<String,Object> options);
    @GET(Endpoints.USER_TOP_ARTISTS)
    Observable<Response<ArtistPage>> fetchUserTopArtists(@Query("user") String user);

    @GET(Endpoints.USER_TOP_ARTISTS)
    Observable<Response<ArtistPage>> fetchUserTopArtists(@Query("user") String user,
                                                       @QueryMap Map<String,Object> options);
    @GET(Endpoints.USER_TOP_TAGS)
    Observable<Response<TagPage>> fetchUserTopTags(@Query("user") String user);

    @GET(Endpoints.USER_TOP_TAGS)
    Observable<Response<TagPage>> fetchUserTopTags(@Query("user") String user,
                                                   @Query("limit") int limit);
    @GET(Endpoints.USER_TOP_TRACKS)
    Observable<Response<TrackPage>> fetchUserTopTracks(@Query("user") String user);

    @GET(Endpoints.USER_TOP_TRACKS)
    Observable<Response<TrackPage>> fetchUserTopTracks(@Query("user") String user,
                                                         @QueryMap Map<String,Object> options);
    @GET(Endpoints.USER_WEEKLY_ALBUM)
    Observable<Response<AlbumPage>> fetchUserWeeklyAlbumChart(@Query("user") String user);

    @GET(Endpoints.USER_WEEKLY_ALBUM)
    Observable<Response<AlbumPage>> fetchUserWeeklyAlbumChart(@Query("user") String user,
                                                              @QueryMap Map<String,Object> options);

    @GET(Endpoints.USER_WEEKLY_ARTIST)
    Observable<Response<ArtistPage>> fetchUserWeeklyArtistChart(@Query("user") String user);

    @GET(Endpoints.USER_WEEKLY_ARTIST)
    Observable<Response<AlbumPage>> fetchUserWeeklyArtistChart(@Query("user") String user,
                                                              @QueryMap Map<String,Object> options);
    @GET(Endpoints.USER_WEEKLY_TRACK)
    Observable<Response<TrackPage>> fetchUserWeeklyTrackChart(@Query("user") String user);

    @GET(Endpoints.USER_WEEKLY_TRACK)
    Observable<Response<TrackPage>> fetchUserWeeklyTrackChart(@Query("user") String user,
                                                              @QueryMap Map<String,Object> options);
    @GET(Endpoints.USER_WEEKLY_CHART)
    Observable<Response<Chart>> fetchUserWeeklyChart(@Query("user") String user);
}
