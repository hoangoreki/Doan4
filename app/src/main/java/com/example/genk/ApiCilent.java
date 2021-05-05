package com.example.genk;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCilent {

    public  static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor= new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
//https://localhost:44385/api
//http://localhost:44385/api        //https://169.254.29.76:45455/api
        // //http://169.254.29.76:45455/api
        //http://doan4.conveyor.cloud/api

        //3 baseurrl này đều ok

       Retrofit retrofit = new Retrofit.Builder()
               .addConverterFactory(GsonConverterFactory.create())
               .baseUrl("https://da4-vh1.conveyor.cloud/")
               .client(okHttpClient)
               .build();

        return  retrofit;
    }
    public  static com.example.genk.UserService getService(){
        com.example.genk.UserService userService = getRetrofit().create(com.example.genk.UserService.class);

        return userService;
    }
}
