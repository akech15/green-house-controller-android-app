package com.nav.greenhousecontoller.integration.greenHouseLimits;


import com.nav.greenhousecontoller.model.GreenHouseLimits;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GreenHouseLimitsService {
    @PUT("limits/{greenHouseId}")
    Call<GreenHouseLimits> updateGreenHouseLimits(@Path("greenHouseId") String greenHouseId, @Body GreenHouseLimits greenHouseLimits);
}
