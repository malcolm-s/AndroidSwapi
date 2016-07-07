package com.malcolmstone.androidswapi;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Malcolm.Stone on 01/07/2016.
 */

public interface SwapiApi {
    @GET("people")
    Observable<People> getPeople();

    @GET("people")
    Observable<People> getPeople(@Query("page") String page);
}
