package com.nav.greenhousecontoller.integration.systemInf;


import com.nav.greenhousecontoller.model.GreenHouseSystemInf;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GreenHouseSystemInfService {
    @PUT("systemInf/{greenHouseId}")
    Call<GreenHouseSystemInf> updateSystemInf(@Path("greenHouseId") String greenHouseId, @Body GreenHouseSystemInf greenHouseSystemInf);
}
