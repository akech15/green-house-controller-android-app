package com.nav.greenhousecontoller.integration.greenHouseLimits;


import com.nav.greenhousecontoller.model.GreenHouseLimits;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface GreenHouseLimitsService {
    @PUT("limits")
    Call<GreenHouseLimits> updateGreenHouseLimits(@Body GreenHouseLimits greenHouseLimits);
}
