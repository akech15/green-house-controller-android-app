package com.nav.greenhousecontoller.integration.userService;


import com.nav.greenhousecontoller.model.GreenHouseResponse;
import com.nav.greenhousecontoller.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface UserService {
    @PUT("userInf")
    Call<GreenHouseResponse> addUser(@Body User user);

    @GET("get-user/{userName}")
    Call<User> getUser(@Path("userName") String userName);

}
