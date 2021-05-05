package com.example.genk;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("/api/da04")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST(" /api/da04")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);
//    @GET(" /api/tbUsers")
//    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);
//    @GET("api/tbUsers")
//    Call<List<Users>> getTaiKhoans() ;
}
