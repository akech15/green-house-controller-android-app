package com.nav.greenhousecontoller.retrofit;


import com.nav.greenhousecontoller.integration.greenHouse.GreenHouseService;
import com.nav.greenhousecontoller.integration.greenHouseLimits.GreenHouseLimitsService;
import com.nav.greenhousecontoller.integration.systemInf.GreenHouseSystemInfService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    public RetrofitBuilder() {
    }

    public static GreenHouseService getGreenHouseService(@NotNull String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(GreenHouseService.class);
    }

    public static GreenHouseLimitsService getGreenHouseLimitsService(@NotNull String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(GreenHouseLimitsService.class);
    }

    public static GreenHouseSystemInfService getGreenHouseSystemInfService(@NotNull String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(GreenHouseSystemInfService.class);
    }
}
