package com.centaury.cataloguemovie.data.remote;

import com.centaury.cataloguemovie.BuildConfig;

/**
 * Created by Centaury on 10/24/2019.
 */
public final class ApiEndPoint {

    public static final String ENDPOINT_DISCOVER_MOVIE = BuildConfig.BASE_URL + "discover/movie";
    public static final String ENDPOINT_GENRE_MOVIE = BuildConfig.BASE_URL + "genre/movie/list";

    public static final String ENDPOINT_DISCOVER_TVSHOW = BuildConfig.BASE_URL + "discover/tv";
    public static final String ENDPOINT_GENRE_TVSHOW = BuildConfig.BASE_URL + "genre/tv/list";


}
