package com.example.mdjahirulislam.simplemap.app;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mdjahirulislam on 28/04/17.
 */

public class AppConfig {
//    public static String BASE_URL = "http://192.168.0.190/";
//    public static String BASE_URL = "http://192.168.43.12/";
    public static String BASE_URL = "http://fullnightfun.com/";

    public static Retrofit getRetrofit() {

        return new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
