package com.example.appbantruyen.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private  static Retrofit retrofit;
    public static Retrofit getRetrofit(){
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.232.170/dulieu/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
