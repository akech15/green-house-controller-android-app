package com.nav.greenhousecontoller.integration.systemInf;


import com.nav.greenhousecontoller.model.GreenHouse;
import com.nav.greenhousecontoller.model.GreenHouseSystemInf;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface GreenHouseSystemInfService {
    @PUT("systemInf")
    Call<GreenHouseSystemInf> updateSystemInf(@Body GreenHouseSystemInf greenHouseSystemInf);
}
