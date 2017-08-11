package com.vpaliy.last_fm_api;

@SuppressWarnings({"UnusedDeclaration"})
public interface Endpoints {

    String ALBUM_INFO="?method=album.getInfo&format=json";
    String ALBUM_TAGS="?method=album.gettags&format=json";
    String ALBUM_TOP_TAGS="?method=album.gettoptags&format=json";
    String ALBUM_SEARCH="?method=album.search&format=json";

    String ARTIST_CORRECTION="?method=artist.getcorrection&format=json";
    String ARTIST_INFO="?method=artist.getinfo&format=json";
    String ARTIST_SIMILAR="?method=artist.getsimilar&format=json";
    String ARTIST_TAGS="?method=artist.gettags&format=json";
    String ARTIST_TOP_ALBUMS="?method=artist.gettopablums&format=json";
    String ARTIST_TOP_TAGS="?method=artist.gettoptags&format=json";
    String ARTIST_TOP_TRACKS="?method=artist.gettoptracks&format=json";
    String ARTIST_SEARCH="?method=artist.search&format=json";

    String TOKEN="?method=auth.gettoken&format=json";

    String SESSION="?method=auth.getmobilesession&format=json";

    String CHART_TOP_ARTISTS="?method=chart.gettopartists&format=json";
    String CHART_TOP_TAGS="?method=chart.gettoptags&format=json";
    String CHART_TOP_TRACKS="?method=chart.gettoptracks&format=json";

    String GEO_TOP_ARTISTS="?method=geo.gettopartists&format=json";
    String GEO_TOP_TRACKS="?method=geo.gettoptracks&format=json";

    String LIBRARY_ARTISTS="?method=library.getArtists&format=json";

    String TAG_INFO="?method=tag.getinfo&format=json";
    String TAG_SIMILAR="?method=tag.getsimilar&format=json";
    String TAG_TOP_ALBUMS="?method=tag.gettopalbums&format=json";
    String TAG_TOP_ARTISTS="?method=tag.gettopartists&format=json";
    String TAG_TOP_TAGS="?method=tag.gettoptags&format=json";
    String TAG_TOP_TRACKS="?method=tag.gettoptracks&format=json";
    String TAG_WEEKLY_CHART="?method=tag.getweeklychartlist&format=json";

    String TRACK_CORRECTION="?method=track.getcorrection&format=json";
    String TRACK_INFO="?method=track.getinfo&format=json";
    String TRACK_SIMILAR="?method=track.getsimilar&format=json";
    String TRACK_TAGS="?method=track.gettags&format=json";
    String TRACK_TOP_TAGS="?method=track.gettoptags&format=json";
    String TRACK_SEARCH="?method=track.search&format=json";

    String USER_ARTIST_TRACKS="?method=user.getartisttracks&format=json";
    String USER_FRIENDS="?method=user.getfriends&format=json";
    String USER_INFO="?method=user.getinfo&format=json";
    String USER_LOVED_TRACKS="?method=user.getlovedtracks&format=json";
    String USER_PERSONAL_TAGS="?method=user.getpersonaltags&format=json";
    String USER_RECENT_TRACKS="?method=user.getrecenttracks&format=json";
    String USER_TOP_ALBUMS="?method=user.gettopalbums&format=json";
    String USER_TOP_ARTISTS="?method=user.gettopartists&format=json";
    String USER_TOP_TAGS="?method=user.gettoptags&format=json";
    String USER_TOP_TRACKS="?method=user.gettoptracks&format=json";
    String USER_WEEKLY_ALBUM="?method=user.getweeklyalbumchart&format=json";
    String USER_WEEKLY_ARTIST="?method=user.getweeklyartistchart&format=json";
    String USER_WEEKLY_CHART="?method=user.getweeklychartlist&format=json";
    String USER_WEEKLY_TRACK="?method=user.getweeklytrackchart&format=json";
}
