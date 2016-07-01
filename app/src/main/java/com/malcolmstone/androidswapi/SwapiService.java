package com.malcolmstone.androidswapi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Malcolm.Stone on 30/06/2016.
 */

public class SwapiService {
    private static String BASE_URL = "http://swapi.co/api/";

    public static SwapiApi createSwapiService() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL);

        return builder.build().create(SwapiApi.class);
    }
}
