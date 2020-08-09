package com.nav.greenhousecontoller.integration.greenHouse;

import com.nav.greenhousecontoller.model.GreenHouse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GreenHouseService {
    @GET("get-green-house/{greenHouseId}")
    Call<GreenHouse> getGreenHouse(@Path("greenHouseId") long greenHouseId);
}
